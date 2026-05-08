package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.OrderRepository;
import com.example.BEDACS3.Service.model.order.OrderDTO;
import com.example.BEDACS3.Service.model.order.OrderItemDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public List<OrderDTO> findOrdersByUserAndStatus(String email, int tabStatus) {
        List<OrderDTO> list = new ArrayList<>();
        String deliveryStatusStr = (tabStatus == 0) ? "pending" : (tabStatus == 1 ? "shipping" : "delivered");

        // SQL mới: Chỉ lấy thông tin đơn hàng và địa chỉ (Không gọi orderdetails và product_images nữa)
        String sql = "SELECT o.id, o.total, o.created_at, " +
                "       CONCAT(ad.streetDetail, ', ', w.name, ', ', p.name) as fullAddress " +
                "FROM orders o " +
                "JOIN users u ON o.userId = u.id " +
                "LEFT JOIN address ad ON o.addressid = ad.id " +
                "LEFT JOIN provinces p ON ad.proviceId = p.id " +
                "LEFT JOIN wards w ON ad.wardId = w.id " +
                "WHERE u.email = ? AND o.deliveryStatus = ? " +
                "ORDER BY o.created_at DESC"; // Sắp xếp đơn mới nhất lên đầu

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, deliveryStatusStr);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderDTO order = new OrderDTO();
                order.setId(rs.getInt("id"));
                order.setTotalPrice(rs.getDouble("total"));
                order.setStatus(tabStatus);
                order.setOrderDate(rs.getTimestamp("created_at").toString());
                order.setAddressDetail(rs.getString("fullAddress"));

                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<OrderItemDTO> findOrderItemsByOrderId(int orderId) {
        List<OrderItemDTO> list = new ArrayList<>();

        // SQL lấy: Tên cá, Giá, Số lượng VÀ 1 ảnh đại diện của con cá đó
        String sql = "SELECT od.productName, od.price, od.productQuantity, " +
                "       (SELECT image_path FROM product_images WHERE product_id = od.productId LIMIT 1) as image_path " +
                "FROM orderdetails od " +
                "WHERE od.orderId = ?";

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItemDTO item = new OrderItemDTO();
                item.setProductName(rs.getString("productName"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("productQuantity"));
                item.setProductImage(rs.getString("image_path"));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
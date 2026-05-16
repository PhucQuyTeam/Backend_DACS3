package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.OrderRepository;
import com.example.BEDACS3.Service.model.cart.CartItemRequest;
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

        String sql = "SELECT o.id, o.total, o.created_at, o.status, " +
                "       CONCAT(ad.streetDetail, ', ', w.name, ', ', p.name) as fullAddress " +
                "FROM orders o " +
                "JOIN users u ON o.userId = u.id " +
                "LEFT JOIN address ad ON o.addressid = ad.id " +
                "LEFT JOIN provinces p ON ad.proviceId = p.id " +
                "LEFT JOIN wards w ON ad.wardId = w.id " +
                "WHERE u.email = ? AND o.deliveryStatus = ? " +
                "ORDER BY o.created_at DESC";

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

                order.setPaymentStatus(rs.getString("status"));

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

        // ĐÃ SỬA: Thêm "od.productId, " (có dấu phẩy) vào đầu câu SELECT
        String sql = "SELECT od.productId, od.productName, od.price, od.productQuantity, " +
                "       (SELECT image_path FROM product_images WHERE product_id = od.productId LIMIT 1) as image_path " +
                "FROM orderdetails od " +
                "WHERE od.orderId = ?";

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderItemDTO item = new OrderItemDTO();
                item.setProductId(rs.getInt("productId")); // Lấy đúng productId
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
    public boolean placeOrder(int userId, int addressId, double totalAmount, String paymentMethod, List<CartItemRequest> items) {
        Connection conn = null;
        try {
            conn = DatabaseDA.getConnection();
            conn.setAutoCommit(false); // BẬT CHẾ ĐỘ TRANSACTION
            String orderStatus = "ZaloPay".equalsIgnoreCase(paymentMethod) ? "paid" : "pending";


            // 1. Lưu vào bảng orders
            String sqlOrder = "INSERT INTO orders (userId, addressid, total, status, deliveryStatus, created_at) VALUES (?, ?, ?, ?, ?, NOW())";
            PreparedStatement psOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            psOrder.setInt(1, userId);
            psOrder.setInt(2, addressId);
            psOrder.setDouble(3, totalAmount);
            psOrder.setString(4, orderStatus); // Chưa thanh toán
            psOrder.setString(5, "pending"); // Đang chờ xác nhận giao hàng
            psOrder.executeUpdate();

            // Lấy orderId vừa được tạo
            ResultSet rs = psOrder.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // 2. Lưu chi tiết đơn hàng (orderdetails) & Xóa giỏ hàng (cart)
            // (Sếp nhớ check lại cột trong bảng orderdetails của sếp là order_id hay orderId nhé)
            String sqlDetail = "INSERT INTO orderdetails (orderId, productId, productQuantity, price, productName) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psDetail = conn.prepareStatement(sqlDetail);

            String sqlDeleteCart = "DELETE FROM cart WHERE id = ?";
            PreparedStatement psDeleteCart = conn.prepareStatement(sqlDeleteCart);

            for (CartItemRequest item : items) {
                // Đẩy vào lệnh thêm chi tiết
                psDetail.setInt(1, orderId);
                psDetail.setInt(2, item.getProductId());
                psDetail.setInt(3, item.getQuantity());
                psDetail.setDouble(4, item.getPrice());

                // BỔ SUNG: Nhét tên sản phẩm vào tham số thứ 5
                psDetail.setString(5, item.getProductName());

                psDetail.addBatch();

                // Đẩy vào lệnh xóa giỏ hàng
                psDeleteCart.setInt(1, item.getCartId());
                psDeleteCart.addBatch();
            }

            psDetail.executeBatch();
            psDeleteCart.executeBatch();
            conn.commit(); // NẾU MỌI THỨ OK -> LƯU THẬT SỰ VÀO DB
            return true;
        } catch (Exception e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) {} // CÓ LỖI -> HOÀN TÁC TOÀN BỘ
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ex) {}
            }
        }
    }
}
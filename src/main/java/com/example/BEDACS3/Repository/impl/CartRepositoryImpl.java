package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.CartRepository;

import com.example.BEDACS3.Service.model.cart.CartItemDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository {

    @Override
    public void addToCart(int userId, int productId, int quantity) {
        // Cú pháp siêu việt: Đã có trong giỏ thì cộng dồn số lượng, chưa có thì thêm mới
        String sql = "INSERT INTO cart (userId, productId, quantity) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity)";

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CartItemDTO> getMyCart(int userId) {
        List<CartItemDTO> list = new ArrayList<>();
        // JOIN bảng cart với products và product_images để lấy đầy đủ thông tin cho Android
        String sql = "SELECT c.id as cartId, c.quantity, p.id as productId, p.name, p.price, " +
                "       (SELECT image_path FROM product_images WHERE product_id = p.id LIMIT 1) as image_path " +
                "FROM cart c " +
                "JOIN products p ON c.productId = p.id " +
                "WHERE c.userId = ?";

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CartItemDTO item = new CartItemDTO();
                item.setCartId(rs.getInt("cartId"));
                item.setProductId(rs.getInt("productId"));
                item.setProductName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));
                item.setProductImage(rs.getString("image_path"));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void removeCartItem(int cartId) {
        String sql = "DELETE FROM cart WHERE id = ?";
        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
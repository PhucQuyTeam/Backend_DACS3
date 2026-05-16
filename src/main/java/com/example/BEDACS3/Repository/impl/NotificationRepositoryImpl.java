package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class NotificationRepositoryImpl {

    // Hàm này sẽ được gọi mỗi khi có một hành động mới (Đặt hàng, Hủy đơn...)
    public boolean insertNotification(int userId, String title, String message) {
        String sql = "INSERT INTO notifications (userId, title, message, is_read, created_at) VALUES (?, ?, ?, 0, NOW())";
        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, title);
            ps.setString(3, message);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
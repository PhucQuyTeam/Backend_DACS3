package com.example.BEDACS3.controller;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.impl.UserRepositoryImpl;
import com.example.BEDACS3.Service.model.notification.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private UserRepositoryImpl userRepository;

    @GetMapping("/my-notifications")
    public ResponseEntity<?> getMyNotifications() {
        try {
            // Lấy email từ Token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            // Tìm userId từ email
            int userId = userRepository.findByEmail(email).getId();

            List<NotificationDTO> list = new ArrayList<>();
            // Lấy thông báo của user này, cái nào mới nhất đưa lên đầu
            String sql = "SELECT * FROM notifications WHERE userId = ? ORDER BY created_at DESC";

            try (Connection conn = DatabaseDA.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    NotificationDTO noti = new NotificationDTO();
                    noti.setId(rs.getInt("id"));
                    noti.setTitle(rs.getString("title"));
                    noti.setMessage(rs.getString("message"));
                    noti.setRead(rs.getBoolean("is_read"));
                    noti.setCreatedAt(rs.getTimestamp("created_at").toString());
                    list.add(noti);
                }
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
        }
    }
}
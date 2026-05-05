package com.example.BEDACS3.controller;

import com.example.BEDACS3.Repository.entity.UserEntity;
import com.example.BEDACS3.Repository.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepositoryImpl userRepository;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        // 1. Lấy email của người đang đăng nhập từ SecurityContext (đã được JwtAuthFilter xác nhận)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // 2. Tìm user trong Database
        UserEntity user = userRepository.findByEmail(email);

        // 3. Đóng gói dữ liệu trả về cho Android
        Map<String, Object> profileData = new HashMap<>();
        profileData.put("id", user.getId());
        profileData.put("name", user.getName()); // Android cần biến "name"
        profileData.put("email", user.getEmail());
        profileData.put("avatar", null); // Tạm thời để null nếu chưa có tính năng up ảnh

        // Bạn có thể query thêm số đơn hàng vào đây:
        profileData.put("pendingCount", 2);
        profileData.put("shippingCount", 5);
        profileData.put("completedCount", 12);

        return ResponseEntity.ok(profileData);
    }
}
package com.example.BEDACS3.Service.impl;

import com.example.BEDACS3.Repository.entity.UserEntity;
import com.example.BEDACS3.Repository.impl.UserRepositoryImpl;
import com.example.BEDACS3.Service.AuthService;
import com.example.BEDACS3.Service.model.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepositoryImpl userRepository; // Gọi xuống tầng DAO/Repository

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthResponse login(LoginRequest request) {
        UserEntity userEntity = userRepository.findByEmail(request.getEmail());

        if (userEntity == null) {
            return new AuthResponse(false, "Email không tồn tại!", null, null);
        }

        if (passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            // Chuyển đổi từ Entity (DB) sang DTO (View)
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userEntity.getId());
            userDTO.setName(userEntity.getName());
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setPhone(userEntity.getNumberPhone());
            userDTO.setAvatar(userEntity.getAvatar());

            String token = UUID.randomUUID().toString();
            return new AuthResponse(true, "Đăng nhập thành công", userDTO, token);
        }

        return new AuthResponse(false, "Sai mật khẩu!", null, null);
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(false, "Email đã được sử dụng!", null, null);
        }

        UserEntity newEntity = new UserEntity();
        newEntity.setName(request.getName() != null ? request.getName() : "Người dùng mới");
        newEntity.setUsername(request.getEmail()); // Tạm lấy email làm username
        newEntity.setEmail(request.getEmail());
        newEntity.setNumberPhone(request.getNumberPhone());
        newEntity.setRoleid(2); // Mặc định role user

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        newEntity.setPassword(hashedPassword);

        UserEntity savedEntity = userRepository.save(newEntity);

        if (savedEntity != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(savedEntity.getId());
            userDTO.setName(savedEntity.getName());
            userDTO.setEmail(savedEntity.getEmail());
            userDTO.setPhone(savedEntity.getNumberPhone());
            return new AuthResponse(true, "Đăng ký thành công", userDTO, null);
        }

        return new AuthResponse(false, "Lỗi server khi lưu database", null, null);
    }
}
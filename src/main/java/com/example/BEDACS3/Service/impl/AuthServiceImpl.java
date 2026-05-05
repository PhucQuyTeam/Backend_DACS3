package com.example.BEDACS3.Service.impl;

import com.example.BEDACS3.Repository.entity.UserEntity;
import com.example.BEDACS3.Repository.impl.UserRepositoryImpl;
import com.example.BEDACS3.Service.AuthService;
import com.example.BEDACS3.Service.model.auth.*;
import com.example.BEDACS3.security.JwtUtils; // Import JwtUtils
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private JwtUtils jwtUtils; // Tiêm JwtUtils vào

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthResponse login(LoginRequest request) {
        UserEntity userEntity = userRepository.findByEmail(request.getEmail());
        if (userEntity == null) return new AuthResponse(false, "Email không tồn tại!", null, null, null);

        if (passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getNumberPhone(), userEntity.getAvatar());

            // TẠO CẢ 2 TOKEN
            String accessToken = jwtUtils.generateToken(userEntity.getEmail());
            String refreshToken = jwtUtils.generateRefreshToken(userEntity.getEmail());

            // Trả về cả hai
            return new AuthResponse(true, "Đăng nhập thành công", userDTO, accessToken, refreshToken);
        }
        return new AuthResponse(false, "Sai mật khẩu!", null, null, null);
    }

    // Viết thêm hàm này xuống dưới cùng
    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        // 1. Kiểm tra Refresh Token có hợp lệ/hết hạn không
        if (requestRefreshToken != null && jwtUtils.validateToken(requestRefreshToken)) {
            // 2. Lấy email từ token cũ
            String email = jwtUtils.getEmailFromToken(requestRefreshToken);

            // 3. (Tuỳ chọn nhưng khuyên dùng) Check xem user này còn tồn tại trong DB không
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity != null) {
                // 4. In Access Token mới cho khách
                String newAccessToken = jwtUtils.generateToken(email);
                return new AuthResponse(true, "Làm mới token thành công", null, newAccessToken, requestRefreshToken);
            }
        }
        return new AuthResponse(false, "Refresh Token không hợp lệ hoặc đã hết hạn. Vui lòng đăng nhập lại!", null, null, null);
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        // ... (Giữ nguyên code hàm register y như cũ) ...
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(false, "Email đã được sử dụng!", null, null,null);
        }

        UserEntity newEntity = new UserEntity();
        newEntity.setName(request.getName() != null ? request.getName() : "Người dùng mới");
        newEntity.setUsername(request.getEmail());
        newEntity.setEmail(request.getEmail());
        newEntity.setNumberPhone(request.getNumberPhone());
        newEntity.setRoleid(2);

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        newEntity.setPassword(hashedPassword);

        UserEntity savedEntity = userRepository.save(newEntity);

        if (savedEntity != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(savedEntity.getId());
            userDTO.setName(savedEntity.getName());
            userDTO.setEmail(savedEntity.getEmail());
            userDTO.setPhone(savedEntity.getNumberPhone());
            return new AuthResponse(true, "Đăng ký thành công", userDTO, null,null);
        }

        return new AuthResponse(false, "Lỗi server khi lưu database", null, null,null);
    }
}
package com.example.BEDACS3.controller;

import com.example.BEDACS3.Repository.entity.UserEntity;
import com.example.BEDACS3.Repository.impl.UserRepositoryImpl;
import com.example.BEDACS3.Service.model.address.AddressRequest;
import com.example.BEDACS3.Service.model.auth.UpdateProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        Map<String, Integer> orderCounts = userRepository.getOrderCounts(user.getId());

        // 3. Đóng gói dữ liệu trả về cho Android
        Map<String, Object> profileData = new HashMap<>();
        profileData.put("id", user.getId());
        profileData.put("name", user.getName()); // Android cần biến "name"
        profileData.put("email", user.getEmail());
        profileData.put("avatar", user.getAvatar());
        profileData.put("numberPhone", user.getNumberPhone());

        profileData.put("pendingCount", orderCounts.get("pendingCount"));
        profileData.put("shippingCount", orderCounts.get("shippingCount"));
        profileData.put("completedCount", orderCounts.get("completedCount"));

        return ResponseEntity.ok(profileData);
    }
    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam(value = "file", required = false) MultipartFile file) { // required = false nghĩa là không upload ảnh mới cũng không sao

        try {
            // 1. Lấy thông tin user đang đăng nhập
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            UserEntity currentUser = userRepository.findByEmail(email);

            // 2. Xử lý lưu file ảnh (Nếu có gửi ảnh mới lên)
            String fileName = currentUser.getAvatar(); // Mặc định giữ lại tên ảnh cũ

            if (file != null && !file.isEmpty()) {
                // Tạo tên file mới để không bị trùng (Thêm timestamp)
                fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                // Đường dẫn lưu file vào thư mục upload/ ở ngoài root
                Path path = Paths.get("upload/" + fileName);
                // Copy file từ request vào thư mục
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }

            // 3. Gọi DB cập nhật thông tin
            boolean isUpdated = userRepository.updateUserInfo(email, name, phone, fileName);

            Map<String, Object> response = new HashMap<>();
            if (isUpdated) {
                response.put("success", true);
                response.put("message", "Cập nhật thông tin thành công!");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Lỗi cập nhật CSDL!");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (IOException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Lỗi lưu file ảnh!");
            return ResponseEntity.status(500).body(response);
        }
    }
    @GetMapping("/address/my-addresses")
    public ResponseEntity<?> getMyAddresses() {
        // 1. Lấy email từ Token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // 2. Từ email tìm ra thông tin User để lấy ID
        UserEntity currentUser = userRepository.findByEmail(email);
        int userId = currentUser.getId();

        // 3. Truyền ID vào hàm để lấy danh sách địa chỉ
        return ResponseEntity.ok(userRepository.getMyAddresses(userId));
    }
    // [POST] Thêm địa chỉ mới
    @PostMapping("/address/add")
    public ResponseEntity<?> addAddress(@RequestBody AddressRequest req) {
        // Lấy thông tin user từ Token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity currentUser = userRepository.findByEmail(email);
        int userId = currentUser.getId();

        boolean success = userRepository.insertAddress(userId, req.provinceId, req.wardId, req.streetDetail);

        Map<String, Object> res = new HashMap<>();
        res.put("success", success);
        res.put("message", success ? "Thêm địa chỉ thành công" : "Lỗi khi thêm địa chỉ");
        return success ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }

    // [PUT] Cập nhật địa chỉ
    @PutMapping("/address/update")
    public ResponseEntity<?> updateAddress(@RequestBody AddressRequest req) {
        // Lấy thông tin user từ Token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity currentUser = userRepository.findByEmail(email);
        int userId = currentUser.getId();

        boolean success = userRepository.updateAddress(req.id, userId, req.provinceId, req.wardId, req.streetDetail);

        Map<String, Object> res = new HashMap<>();
        res.put("success", success);
        res.put("message", success ? "Cập nhật thành công" : "Địa chỉ không tồn tại hoặc không thuộc quyền sở hữu của bạn");
        return success ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }

    // [DELETE] Xóa địa chỉ
    @DeleteMapping("/address/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable int id) {
        // Lấy thông tin user từ Token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity currentUser = userRepository.findByEmail(email);
        int userId = currentUser.getId();

        boolean success = userRepository.deleteAddress(id, userId);

        return success ? ResponseEntity.ok("Đã xóa địa chỉ") : ResponseEntity.badRequest().body("Không thể xóa địa chỉ này");
    }
    // [GET] API lấy danh sách Tỉnh/Thành
    @GetMapping("/address/provinces")
    public ResponseEntity<?> getAllProvinces() {
        return ResponseEntity.ok(userRepository.getAllProvinces());
    }

    // [GET] API lấy danh sách Phường/Xã dựa theo Tỉnh
    @GetMapping("/address/wards")
    public ResponseEntity<?> getWardsByProvince(@RequestParam("provinceId") int provinceId) {
        return ResponseEntity.ok(userRepository.getWardsByProvinceId(provinceId));
    }
}


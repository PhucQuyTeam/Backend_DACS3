package com.example.BEDACS3.controller;

import com.example.BEDACS3.Repository.impl.UserRepositoryImpl;
import com.example.BEDACS3.Repository.impl.reviewRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("*")
public class ReviewApiController {

    // TRỎ THẲNG VÀO THƯ MỤC XAMPP NHƯ SẾP YÊU CẦU
    private final String UPLOAD_DIR = "C:/xampp/htdocs/uploads/";

    @Autowired
    private reviewRepositoryImpl reviewRepo;

    @Autowired
    private UserRepositoryImpl userRepo;

    @PostMapping("/add")
    public ResponseEntity<?> addReview(
            @RequestParam("productId") int productId,
            @RequestParam("orderId") int orderId,
            @RequestParam("rating") int rating,
            @RequestParam(value = "comment", required = false, defaultValue = "") String comment,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {

        try {
            // 1. DÙNG TOKEN ĐỂ BIẾT AI LÀ NGƯỜI ĐÁNH GIÁ (Bảo mật 100%)
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            int userId = userRepo.findByEmail(email).getId();

            // 2. Chặn hack (Nếu nó dùng Postman spam)
            if (reviewRepo.hasReviewed(userId, productId, orderId)) {
                return ResponseEntity.badRequest().body("Sếp đã đánh giá sản phẩm này rồi!");
            }

            // 3. Xử lý lưu ảnh vào thư mục XAMPP
            String fileName = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + fileName);
                if (!Files.exists(path.getParent())) Files.createDirectories(path.getParent());
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }

            // 4. Lưu vào Database
            boolean success = reviewRepo.insertReview(userId, productId, orderId, rating, comment, fileName);

            if (success) return ResponseEntity.ok("Đánh giá thành công!");
            else return ResponseEntity.badRequest().body("Lỗi khi lưu CSDL.");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi Server: " + e.getMessage());
        }
    }
}
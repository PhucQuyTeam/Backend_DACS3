package com.example.BEDACS3.controller;
import com.example.BEDACS3.Repository.impl.productRepositoryImpl;
import com.example.BEDACS3.Repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductApiController {

    private final String UPLOAD_DIR = "upload/";

    @Autowired
    private productRepositoryImpl productRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("quantity") int quantity,
            @RequestParam("categoryId") int categoryId,
            @RequestParam(value = "description", required = false, defaultValue = "") String description,
            MultipartHttpServletRequest request) { // Sử dụng request để lấy tất cả file

        Map<String, Object> response = new HashMap<>();

        try {
            // 1. Lưu thông tin sản phẩm và lấy ID
            int productId = productRepository.insertProductAndGetId(name, description, price, quantity, categoryId);

            if (productId > 0) {
                int count = 0;

                // 2. Lấy tất cả các file từ request (thumbnail, img1, img2, img3...)
                Map<String, MultipartFile> fileMap = request.getFileMap();

                for (MultipartFile file : fileMap.values()) {
                    if (!file.isEmpty()) {
                        // Lưu file vật lý vào thư mục upload/
                        String fileName = saveFile(file);

                        // Lưu đường dẫn vào bảng product_images gắn với productId
                        productRepository.insertImageRelative(fileName, productId);
                        count++;
                    }
                }

                response.put("success", true);
                response.put("message", "Đã thêm sản phẩm #" + productId + " cùng " + count + " ảnh.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Lỗi khi tạo sản phẩm vào Database.");
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi Server: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    // Hàm phụ trợ lưu file vào thư mục upload/
    private String saveFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);

        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
}
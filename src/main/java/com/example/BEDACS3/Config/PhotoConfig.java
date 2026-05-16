package com.example.BEDACS3.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PhotoConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Đường dẫn tuyệt đối tới thư mục uploads của XAMPP trên ổ C
        // Lưu ý: Dùng dấu gạch chéo xuôi (/) và phải có dấu (/) ở cuối cùng
        String externalUploadPath = "file:C:/xampp/htdocs/uploads/";

        // Khi App gọi link http://localhost:8081/upload/ten_anh.jpg
        // Spring Boot sẽ tự động chui vào C:/xampp/htdocs/uploads/ để lấy ảnh ra
        registry.addResourceHandler("/upload/**")
                .addResourceLocations(externalUploadPath);
    }
}
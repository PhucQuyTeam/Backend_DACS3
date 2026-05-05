package com.example.BEDACS3.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PhotoConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Ánh xạ đường dẫn URL bắt đầu bằng /uploads/ tới thư mục vật lý "uploads" ở gốc dự án
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
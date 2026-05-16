package com.example.BEDACS3.Service.impl;

import com.example.BEDACS3.Repository.BlogRepository;
import com.example.BEDACS3.Repository.CategoryBlogRepository;
import com.example.BEDACS3.Repository.entity.BlogEntity;
import com.example.BEDACS3.Repository.entity.CategoryBlogEntity;
import com.example.BEDACS3.Service.BlogService;
import com.example.BEDACS3.Service.model.blog.BlogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryBlogRepository categoryBlogRepository;


    @Override
    public List<BlogDTO> getAllBlogs() {
        // 1. Lấy toàn bộ Entity thuần từ bảng Blogs
        List<BlogEntity> listEntities = blogRepository.getAllBlogs();

        // 2. Tạo một list rỗng để chứa DTO sắp chế biến
        List<BlogDTO> listDTOs = new ArrayList<>();

        // 3. Duyệt qua từng Entity và gộp dữ liệu
        for (BlogEntity entity : listEntities) {
            BlogDTO dto = new BlogDTO();

            // Copy các thuộc tính cơ bản sang DTO
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setDescription(entity.getDescription());
            dto.setContent(entity.getContent());
            dto.setImage(entity.getImage());
            dto.setCategoryId(entity.getCategoryId());
            dto.setCreatedAt(entity.getCreatedAt());

            // --- KHÚC GỘP TÊN DANH MỤC VÀO ĐÂY ---
            if (entity.getCategoryId() != null) {
                // Gọi Repository thứ 2 để tìm cái tên
                CategoryBlogEntity categoryEntity = categoryBlogRepository.findById(entity.getCategoryId());
                if (categoryEntity != null) {
                    dto.setCategoryName(categoryEntity.getName()); // Bơm tên danh mục vào DTO
                } else {
                    dto.setCategoryName("Chưa phân loại");
                }
            } else {
                dto.setCategoryName("Chưa phân loại");
            }

            // Đóng gói xong 1 bài thì nhét vào List
            listDTOs.add(dto);
        }

        return listDTOs;
    }
}

package com.example.BEDACS3.Service.impl;

import com.example.BEDACS3.Repository.ReviewRepository;
import com.example.BEDACS3.Repository.UserRepository;
import com.example.BEDACS3.Repository.entity.ReviewEntity;
import com.example.BEDACS3.Repository.entity.UserEntity;
import com.example.BEDACS3.Service.model.products.ReviewDTO;
import com.example.BEDACS3.Service.reviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class reviewServiceImpl implements reviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<ReviewDTO> getReviewListForProduct(Integer productId) {
        List<ReviewDTO> resultList = new ArrayList<>();

        List<ReviewEntity> reviewEntities = reviewRepository.getAllReviewById(productId);

        for (ReviewEntity entity : reviewEntities) {
            ReviewDTO dto = new ReviewDTO();

            dto.setProductId(entity.getProductId());
            dto.setCreatedAt(entity.getCreatedAt());
            dto.setRating(entity.getRating());
            dto.setComment(entity.getComment());
            dto.setImage(entity.getImage());

            // Gọi hàm của UserRepository để lấy thông tin User
            UserEntity user = userRepository.getNameReviewById(entity.getUserId());

            // Kiểm tra xem user có tồn tại không (phòng trường hợp user đã bị xóa tài khoản)
            if (user != null) {
                dto.setReviewerName(user.getName());
            } else {
                dto.setReviewerName("Người dùng ẩn danh");
            }

            // Thêm DTO đã hoàn thiện vào danh sách kết quả
            resultList.add(dto);
        }

        // Trả cục DTO hoàn chỉnh ra Controller
        return resultList;
    }
}

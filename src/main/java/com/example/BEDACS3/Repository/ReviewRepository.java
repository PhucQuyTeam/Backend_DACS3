package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Repository.entity.ReviewEntity;
import com.example.BEDACS3.Repository.entity.ReviewSummary;

import java.util.List;

public interface ReviewRepository {
    ReviewSummary getReviewSummaryByProductId(Integer productId);
    List<ReviewEntity> getAllReviewById(Integer productId);
}

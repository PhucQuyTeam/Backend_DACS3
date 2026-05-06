package com.example.BEDACS3.Service;

import com.example.BEDACS3.Service.model.products.ReviewDTO;

import java.util.List;

public interface reviewService {
    List<ReviewDTO> getReviewListForProduct(Integer productId);
}

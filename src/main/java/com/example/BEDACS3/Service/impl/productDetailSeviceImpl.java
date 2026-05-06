package com.example.BEDACS3.Service.impl;

import com.example.BEDACS3.Repository.*;
import com.example.BEDACS3.Repository.entity.CategoryEntity;
import com.example.BEDACS3.Repository.entity.ProductImageEntity;
import com.example.BEDACS3.Repository.entity.ReviewSummary;
import com.example.BEDACS3.Repository.entity.productEntity;
import com.example.BEDACS3.Service.model.products.productDetailDTO;
import com.example.BEDACS3.Service.productDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class productDetailSeviceImpl implements productDetailService {

    @Autowired
    private productRepository productRepository;
    @Autowired
    private productImageRepository productImageRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private categoryRepository categoryRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public productDetailDTO getProductDetailById(Integer productId) {
        productEntity product = productRepository.getProductbyId(productId);

        if (product == null) {
            return null;
        }

        List<ProductImageEntity> imageEntities = productImageRepository.findAllImageByProductId(productId);
        List<String> imageUrls = new ArrayList<>();
        for(ProductImageEntity img : imageEntities){
            imageUrls.add(img.getImagePath());
        }

        Integer totalSold = orderDetailRepository.getTotalQuantityByProductId(productId);

        CategoryEntity category = categoryRepository.getCategoryById(productId);
        String categoryName = (category !=null) ? category.getName():"chưa phần loại";

        ReviewSummary reviewSummary = reviewRepository.getReviewSummaryByProductId(productId);

        productDetailDTO productDetailDTO = new productDetailDTO();
        productDetailDTO.setProductId(product.getId());
        productDetailDTO.setName(product.getName());
        productDetailDTO.setPrice(product.getPrice());
        productDetailDTO.setDescription(product.getDescription());
        productDetailDTO.setQuantity(product.getQuantity());

        productDetailDTO.setImgages(imageUrls);
        productDetailDTO.setTotal_ProductQuantity(totalSold);
        productDetailDTO.setCategorieName(categoryName);

        productDetailDTO.setAverageRating(reviewSummary.getAverageRating());
        productDetailDTO.setTotalReviews(reviewSummary.getTotalReviews());

        return productDetailDTO;
    }
}

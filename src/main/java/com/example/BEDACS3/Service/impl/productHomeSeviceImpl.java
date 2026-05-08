package com.example.BEDACS3.Service.impl;

import com.example.BEDACS3.Repository.*;
import com.example.BEDACS3.Repository.entity.CategoryEntity;
import com.example.BEDACS3.Repository.entity.ProductImageEntity;
import com.example.BEDACS3.Repository.entity.ReviewSummary;
import com.example.BEDACS3.Repository.entity.productEntity;
import com.example.BEDACS3.Service.ProductHomeService;
import com.example.BEDACS3.Service.model.products.ProductHomeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class productHomeSeviceImpl implements ProductHomeService {

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
    public List<ProductHomeDTO> getProducts() {
        List<productEntity> entities = productRepository.findAllProducts();


        List<ProductHomeDTO> dtos = new ArrayList<>();

        for (productEntity entity : entities){
            ProductHomeDTO dto = new ProductHomeDTO();
            dto.setProductId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setQuantity(entity.getQuantity());

            Integer productID = entity.getId();

            ProductImageEntity productImageEntity = productImageRepository.findFirstImageByProductId(productID);
            if (productImageEntity != null && productImageEntity.getImagePath() != null) {
                dto.setImg(productImageEntity.getImagePath());
            }

            CategoryEntity category = categoryRepository.getCategoryById(productID);
            if(category != null){
                dto.setCategorieId(category.getId());
            }

            ReviewSummary reviewSummary = reviewRepository.getReviewSummaryByProductId(productID);
            dto.setAverageRating(reviewSummary.getAverageRating());

            Integer total_proQuantity = orderDetailRepository.getTotalQuantityByProductId(productID);
            if(total_proQuantity !=null || total_proQuantity !=0){
                dto.setTotal_ProductQuantity(total_proQuantity);
            }

            dtos.add(dto);
        }
        return dtos;
    }
}

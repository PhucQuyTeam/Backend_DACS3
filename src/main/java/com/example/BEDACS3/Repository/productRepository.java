package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Repository.entity.productAiEntity;
import com.example.BEDACS3.Repository.entity.productEntity;

import java.util.List;

public interface productRepository {
    List<productEntity> findAllProducts();
    productEntity getProductbyId(Integer productId);
    List<productAiEntity> getAllProducts();
}

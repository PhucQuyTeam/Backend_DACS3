package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Repository.entity.ProductImageEntity;

import java.util.List;

public interface productImageRepository {
    ProductImageEntity findFirstImageByProductId(Integer productId);
    List<ProductImageEntity> findAllImageByProductId(Integer productId);
}

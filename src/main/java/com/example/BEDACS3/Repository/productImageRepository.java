package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Repository.entity.ProductImageEntity;

public interface productImageRepository {
    ProductImageEntity findFirstImageByProductId(Integer productId);
}

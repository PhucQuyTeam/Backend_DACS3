package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Repository.entity.CategoryEntity;

public interface categoryRepository {
    CategoryEntity getCategoryById(Integer productId);
}

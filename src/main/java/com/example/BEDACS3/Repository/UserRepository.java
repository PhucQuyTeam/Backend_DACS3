package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Repository.entity.UserEntity;

public interface UserRepository {
    UserEntity findByEmail(String email);
    boolean existsByEmail(String email);
    UserEntity save(UserEntity userEntity);
    UserEntity getNameReviewById(Integer userId);
    boolean updateCartQuantity(int cartId, int quantity);
}
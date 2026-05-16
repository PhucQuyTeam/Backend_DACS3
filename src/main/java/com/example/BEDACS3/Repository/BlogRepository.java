package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Repository.entity.BlogEntity;

import java.util.List;

public interface BlogRepository {
    List<BlogEntity> getAllBlogs();
}

package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.CategoryBlogRepository;
import com.example.BEDACS3.Repository.entity.CategoryBlogEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class CategoryBlogRepositoryImpl implements CategoryBlogRepository {
    @Override
    public CategoryBlogEntity findById(int id) {
        CategoryBlogEntity categoryEntity = null;

        String sql = "SELECT id, name FROM categories_blog WHERE id = ?";

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoryEntity = new CategoryBlogEntity();
                    categoryEntity.setId(rs.getInt("id"));
                    categoryEntity.setName(rs.getString("name"));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy Category Blog theo ID: ", e);
        }

        return categoryEntity;
    }
}

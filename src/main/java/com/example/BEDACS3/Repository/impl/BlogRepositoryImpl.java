package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.BlogRepository;
import com.example.BEDACS3.Repository.entity.BlogEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogRepositoryImpl implements BlogRepository {

    @Override
    public List<BlogEntity> getAllBlogs() {
        List<BlogEntity> listBlogs = new ArrayList<>();

        String sql = "SELECT id, title, description, content, image, category_id, created_at FROM blogs ORDER BY created_at DESC";

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BlogEntity entity = new BlogEntity();
                entity.setId(rs.getInt("id"));
                entity.setTitle(rs.getString("title"));
                entity.setDescription(rs.getString("description"));
                entity.setContent(rs.getString("content"));
                entity.setImage(rs.getString("image"));

                int categoryId = rs.getInt("category_id");
                if (rs.wasNull()) {
                    entity.setCategoryId(null);
                } else {
                    entity.setCategoryId(categoryId);
                }

                entity.setCreatedAt(rs.getTimestamp("created_at"));

                listBlogs.add(entity);
            }

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách Blog: ", e);
        }

        return listBlogs;
    }
}

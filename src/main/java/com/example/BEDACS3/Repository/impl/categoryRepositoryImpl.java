package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.categoryRepository;
import com.example.BEDACS3.Repository.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class categoryRepositoryImpl implements categoryRepository {
    @Override
    public CategoryEntity getCategoryById(Integer productId) {
        CategoryEntity categoryEntity = new CategoryEntity();

        String sql = "SELECT c.id,c.name FROM categories c "+
                     "INNER JOIN products p ON c.id = p.categoryId "+
                     "Where p.id = ?";
        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,productId);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    categoryEntity.setId(rs.getInt("id"));
                    categoryEntity.setName(rs.getString("name"));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return categoryEntity;
    }
}

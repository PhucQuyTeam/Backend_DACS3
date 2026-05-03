package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.entity.ProductImageEntity;
import com.example.BEDACS3.Repository.entity.productEntity;
import com.example.BEDACS3.Repository.productImageRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class productImageRepositoryImpl implements productImageRepository {

    @Override
    public ProductImageEntity findFirstImageByProductId(Integer productId) {
        ProductImageEntity image = new ProductImageEntity();
        String sql = "SELECT * FROM product_images WHERE product_id = ? LIMIT 1";

        try(Connection conn = DatabaseDA.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    image.setId(rs.getInt("id"));
                    image.setProductId(rs.getInt("product_id"));
                    image.setImagePath(rs.getString("image_path"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return image;
    }
}

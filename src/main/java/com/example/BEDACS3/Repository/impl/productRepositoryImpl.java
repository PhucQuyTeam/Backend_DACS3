package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.entity.productEntity;
import com.example.BEDACS3.Repository.productRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class productRepositoryImpl implements productRepository {

    @Override
    public List<productEntity> findAllProducts() {
        List<productEntity> proList = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try(Connection conn = DatabaseDA.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs  = ps.executeQuery()) {

            while (rs.next()){
                productEntity product = new productEntity();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setCreatedAt(rs.getTimestamp("created_at")); // Khớp tên cột DB
                product.setUpdatedAt(rs.getTimestamp("updated_at")); // Khớp tên cột DB

                proList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proList;
    }
}

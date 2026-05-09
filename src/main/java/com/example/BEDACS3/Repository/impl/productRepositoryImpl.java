package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.entity.productEntity;
import com.example.BEDACS3.Repository.productRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
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

    @Override
    public productEntity getProductbyId(Integer productId) {
        productEntity pro = null;
        String sql = "SELECT * FROM products WHERE id = ?";


        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {


                if (rs.next()) {
                    pro = new productEntity();
                    pro.setId(rs.getInt("id"));
                    pro.setName(rs.getString("name"));
                    pro.setDescription(rs.getString("description"));
                    pro.setPrice(rs.getInt("price"));
                    pro.setQuantity(rs.getInt("quantity"));
                    pro.setCategoryId(rs.getInt("categoryId"));
                    pro.setCreatedAt(rs.getTimestamp("created_at"));
                    pro.setUpdatedAt(rs.getTimestamp("updated_at"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pro;
    }
    // 1. Hàm thêm Sản phẩm (Đã gọt bỏ thumbnail, chỉ còn thông tin chữ)
    public int insertProductAndGetId(String name, String desc, int price, int qty, int catId) {
        // Cập nhật câu SQL không còn cột thumbnail
        String sql = "INSERT INTO products (name, description, price, quantity, categoryId, created_at) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setInt(3, price);
            ps.setInt(4, qty);
            ps.setInt(5, catId);
            ps.executeUpdate();

            // Lấy ID tự tăng vừa tạo ra
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // 2. Hàm lưu Ảnh phụ (Chuẩn 100% theo bảng product_images sếp chụp)
    public void insertImageRelative(String fileName, int productId) {
        // Cập nhật đúng tên bảng và tên cột
        String sql = "INSERT INTO product_images (image_path, product_id) VALUES (?, ?)";
        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fileName);
            ps.setInt(2, productId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.ReviewRepository;
import com.example.BEDACS3.Repository.entity.ReviewEntity;
import com.example.BEDACS3.Repository.entity.ReviewSummary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class reviewRepositoryImpl implements ReviewRepository {
    @Override
    public ReviewSummary getReviewSummaryByProductId(Integer productId) {
        ReviewSummary summary = new ReviewSummary();
        String sql = "SELECT COUNT(id) AS total_reviews, COALESCE(AVG(rating), 0) AS avg_rating " +
                "FROM reviews WHERE product_id = ?";

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    summary.setTotalReviews(rs.getInt("total_reviews"));

                    // Làm tròn số sao đến 1 chữ số thập phân (VD: 4.66667 -> 4.7)
                    double rawAvg = rs.getDouble("avg_rating");
                    double roundedAvg = Math.round(rawAvg * 10.0) / 10.0;
                    summary.setAverageRating(roundedAvg);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return summary;
    }

    @Override
    public List<ReviewEntity> getAllReviewById(Integer productId) {
        List<ReviewEntity> listReviews = new ArrayList<>();

        String sql = "SELECT * FROM reviews WHERE product_id = ? " +
                "ORDER BY created_at DESC";

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // 1. Truyền ID sản phẩm vào
            ps.setInt(1, productId);

            // 2. Chạy câu lệnh và lấy kết quả
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReviewEntity review = new ReviewEntity();
                    review.setUserId(rs.getInt("user_id"));
                    review.setProductId(rs.getInt("product_id"));
                    review.setCreatedAt(rs.getTimestamp("created_at"));
                    review.setRating(rs.getInt("rating"));
                    review.setComment(rs.getString("comment"));
                    review.setImage(rs.getString("image"));

                    listReviews.add(review); // Nhét vào danh sách
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listReviews;
    }
}

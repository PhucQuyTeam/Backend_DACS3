package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.OrderDetailRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    @Override
    public Integer getTotalQuantityByProductId(Integer productId) {
        String sql = "SELECT SUM(productQuantity) AS total_productQuantity FROM orderdetails WHERE productId = ?";

        Integer totalQuantity = 0;
        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalQuantity = rs.getInt("total_productQuantity");

                    // Xử lý trường hợp SUM trả về null trong SQL (nếu ko có dòng dữ liệu nào)
                    if (rs.wasNull()) {
                        totalQuantity = 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalQuantity;
    }
}

package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.ConversationRepository;
import com.example.BEDACS3.Repository.entity.ConversationEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ConversationRepositoryImpl implements ConversationRepository {

    @Override
    public int createConversation(int userId, int adminId) {
        String sql = "INSERT INTO conversations (user_id, admin_id, created_at, updated_at) VALUES (?, ?, NOW(), NOW())";
        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, userId);
            ps.setInt(2, adminId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public ConversationEntity findByUserIdAndAdminId(int userId, int adminId) {
        String sql = "SELECT * FROM conversations WHERE user_id = ? AND admin_id = ?";
        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, adminId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ConversationEntity(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("admin_id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateTimestamp(int conversationId) {
        String sql = "UPDATE conversations SET updated_at = NOW() WHERE id = ?";
        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, conversationId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
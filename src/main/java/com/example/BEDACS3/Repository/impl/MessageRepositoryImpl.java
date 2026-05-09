package com.example.BEDACS3.Repository.impl;

import com.example.BEDACS3.Database.DatabaseDA;
import com.example.BEDACS3.Repository.MessageRepository;
import com.example.BEDACS3.Repository.entity.MessageEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    @Override
    public void saveMessage(int conversationId, int senderId, String message, String messageType) {
        String sql = "INSERT INTO messages (conversation_id, sender_id, message, message_type, is_read, created_at) VALUES (?, ?, ?, ?, false, NOW())";
        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, conversationId);
            ps.setInt(2, senderId);
            ps.setString(3, message);
            ps.setString(4, messageType);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MessageEntity> getMessagesByConversationId(int conversationId) {
        List<MessageEntity> list = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE conversation_id = ? ORDER BY created_at ASC";

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, conversationId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MessageEntity msg = new MessageEntity();
                msg.setId(rs.getInt("id"));
                msg.setConversationId(rs.getInt("conversation_id"));
                msg.setSenderId(rs.getInt("sender_id"));
                msg.setMessage(rs.getString("message"));
                msg.setMessageType(rs.getString("message_type"));
                msg.setRead(rs.getBoolean("is_read"));
                msg.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countUnreadMessages(int userId) {
        int count = 0;
        // Câu SQL: Tìm các phòng chat của user này, đếm các tin nhắn do người khác gửi (admin) và chưa đọc
        String sql = "SELECT COUNT(*) FROM messages m " +
                "JOIN conversations c ON m.conversation_id = c.id " +
                "WHERE c.user_id = ? AND m.sender_id != ? AND m.is_read = false";

        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void markMessagesAsRead(int conversationId, int userId) {
            String sql = "UPDATE messages SET is_read = true WHERE conversation_id = ? AND sender_id != ? AND is_read = false";
        try (Connection conn = DatabaseDA.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, conversationId);
            ps.setInt(2, userId); // Mã của người đang xem chat
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
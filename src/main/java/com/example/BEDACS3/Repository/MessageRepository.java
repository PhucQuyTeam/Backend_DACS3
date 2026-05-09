package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Repository.entity.MessageEntity;

import java.util.List;

public interface MessageRepository {
    void saveMessage(int conversationId, int senderId, String message, String messageType);
    List<MessageEntity> getMessagesByConversationId(int conversationId);
    int countUnreadMessages(int userId);
    void markMessagesAsRead(int conversationId, int userId);
}

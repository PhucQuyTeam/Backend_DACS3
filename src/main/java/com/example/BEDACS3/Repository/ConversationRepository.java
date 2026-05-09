package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Repository.entity.ConversationEntity;

public interface ConversationRepository {
    int createConversation (int userId, int adminId);
    ConversationEntity findByUserIdAndAdminId(int userId, int adminId);
    void updateTimestamp(int conversationId);

}

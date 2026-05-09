package com.example.BEDACS3.Service;

import com.example.BEDACS3.Service.model.chat.ChatDTO;

import java.util.List;

public interface ChatService {
    void processAndSaveMessage(int userId, int adminId, int senderId, String message, String messageType);
    List<ChatDTO> getChatHistory(int userId, int adminId);int getUnreadMessageCount(int userId);
    void markChatAsRead(int userId, int adminId);
}

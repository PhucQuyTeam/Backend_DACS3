package com.example.BEDACS3.Service.impl;

import com.example.BEDACS3.Repository.ConversationRepository;
import com.example.BEDACS3.Repository.MessageRepository;
import com.example.BEDACS3.Repository.UserRepository;
import com.example.BEDACS3.Repository.entity.ConversationEntity;
import com.example.BEDACS3.Repository.entity.MessageEntity;
import com.example.BEDACS3.Repository.entity.UserEntity;
import com.example.BEDACS3.Service.ChatService;
import com.example.BEDACS3.Service.model.chat.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void processAndSaveMessage(int userId, int adminId, int senderId, String message, String messageType) {
        ConversationEntity conv = conversationRepository.findByUserIdAndAdminId(userId,adminId);
        int convId;
        if(conv == null){
            convId = conversationRepository.createConversation(userId,adminId);
        }else {
            convId = conv.getId();
            conversationRepository.updateTimestamp(convId);
        }

        messageRepository.saveMessage(convId,senderId,message,messageType);
    }

    @Override
    public List<ChatDTO> getChatHistory(int userId, int adminId) {
        ConversationEntity conv = conversationRepository.findByUserIdAndAdminId(userId,adminId);
        List<ChatDTO> dtos = new ArrayList<>();

        if(conv != null){
            List<MessageEntity> entities = messageRepository.getMessagesByConversationId(conv.getId());

            for(MessageEntity entity : entities){
                ChatDTO dto = new ChatDTO();
                dto.setId(entity.getId());
                dto.setConversationId(entity.getConversationId());
                dto.setSenderId(entity.getSenderId());
                dto.setMessage(entity.getMessage());
                dto.setMessageType(entity.getMessageType());
                dto.setRead(entity.isRead());

                if (entity.getCreatedAt() != null) {
                    dto.setCreatedAt(entity.getCreatedAt().toString());
                }

                UserEntity user = userRepository.findById(entity.getSenderId());
                if (user != null) {
                    dto.setSenderName(user.getName());
                    dto.setSenderAvatar(user.getAvatar());
                }

                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Override
    public int getUnreadMessageCount(int userId) {
        return messageRepository.countUnreadMessages(userId);
    }

    @Override
    public void markChatAsRead(int userId, int adminId) {
        // Tìm ID của phòng chat trước
        ConversationEntity conv = conversationRepository.findByUserIdAndAdminId(userId, adminId);
        if (conv != null) {
            // Cập nhật các tin nhắn trong phòng này thành đã đọc
            messageRepository.markMessagesAsRead(conv.getId(), userId);
        }
    }
}

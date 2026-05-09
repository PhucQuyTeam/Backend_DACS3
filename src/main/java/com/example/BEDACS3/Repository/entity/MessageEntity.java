package com.example.BEDACS3.Repository.entity;

import java.sql.Timestamp;

public class MessageEntity {
    private int id;
    private int conversationId;
    private int senderId;
    private String message;
    private String messageType;
    private boolean isRead;
    private Timestamp createdAt;

    public MessageEntity(int id, int conversationId, int senderId, String message, String messageType, boolean isRead, Timestamp createdAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.message = message;
        this.messageType = messageType;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    public MessageEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

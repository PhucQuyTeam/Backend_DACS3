package com.example.BEDACS3.Service.model.notification;

public class NotificationDTO {
    private int id;
    private String title;
    private String message;
    private boolean isRead;
    private String createdAt;

    // Sếp tự Generate Getter và Setter ra nhé
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
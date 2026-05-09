package com.example.BEDACS3.Repository.entity;

public class BaseResponse {
    private boolean success;
    private String message;

    public BaseResponse() {
    }

    public BaseResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // --- Getter và Setter (Bắt buộc để Spring Boot chuyển thành JSON) ---
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

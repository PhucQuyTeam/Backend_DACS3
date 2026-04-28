package com.example.BEDACS3.Service.model.auth;

public class AuthResponse {
    private boolean success;
    private String message;
    private UserDTO user;
    private String token;

    public AuthResponse(boolean success, String message, UserDTO user, String token) {
        this.success = success;
        this.message = message;
        this.user = user;
        this.token = token;
    }

    // --- BẮT BUỘC PHẢI CÓ GETTER/SETTER ĐỂ XUẤT RA JSON ---
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
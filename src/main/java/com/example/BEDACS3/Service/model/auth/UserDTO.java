package com.example.BEDACS3.Service.model.auth;

public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String avatar;

    // Constructor rỗng (Bắt buộc phải có để Spring Boot tự động map dữ liệu JSON)
    public UserDTO() {
    }

    // Constructor đầy đủ tham số
    public UserDTO(Integer id, String name, String email, String phone, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }

    // --- GETTER VÀ SETTER ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
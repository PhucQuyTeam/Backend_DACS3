package com.example.BEDACS3.Service.model.auth;

public class UpdateProfileRequest {
    private String name;
    private String numberPhone;

    // Getter và Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNumberPhone() { return numberPhone; }
    public void setNumberPhone(String numberPhone) { this.numberPhone = numberPhone; }
}
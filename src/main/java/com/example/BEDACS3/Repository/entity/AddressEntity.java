package com.example.BEDACS3.Repository.entity;

import java.sql.Timestamp;

public class AddressEntity {
    private int id;
    private Integer proviceId; // Chữ "provice" giữ nguyên theo cấu trúc bảng của bạn
    private Integer wardId;
    private String streetDetail;
    private Integer userId;
    private Timestamp createAt;
    private Timestamp updatedAt;

    public AddressEntity() {
    }

    public AddressEntity(int id, Integer proviceId, Integer wardId, String streetDetail, Integer userId, Timestamp createAt, Timestamp updatedAt) {
        this.id = id;
        this.proviceId = proviceId;
        this.wardId = wardId;
        this.streetDetail = streetDetail;
        this.userId = userId;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProviceId() {
        return proviceId;
    }

    public void setProviceId(Integer proviceId) {
        this.proviceId = proviceId;
    }

    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    public String getStreetDetail() {
        return streetDetail;
    }

    public void setStreetDetail(String streetDetail) {
        this.streetDetail = streetDetail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}

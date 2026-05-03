package com.example.BEDACS3.Repository.entity;

import java.sql.Timestamp;

public class WardEntity {
    private int id;
    private String name;
    private Integer provicesId; // Chữ "provicesId" giữ nguyên theo DB
    private Timestamp createAt;
    private Timestamp updatedAt;

    public WardEntity() {
    }

    public WardEntity(String name, Timestamp createAt, Timestamp updatedAt) {
        this.name = name;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProvicesId() {
        return provicesId;
    }

    public void setProvicesId(Integer provicesId) {
        this.provicesId = provicesId;
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

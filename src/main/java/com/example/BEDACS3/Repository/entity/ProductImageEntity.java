package com.example.BEDACS3.Repository.entity;

public class ProductImageEntity {
    private int id;
    private Integer productId;
    private String imagePath;

    public ProductImageEntity() {

    }

    public ProductImageEntity(int id, Integer productId, String imagePath) {
        this.id = id;
        this.productId = productId;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

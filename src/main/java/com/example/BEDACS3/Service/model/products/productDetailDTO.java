package com.example.BEDACS3.Service.model.products;

import com.example.BEDACS3.Repository.entity.ReviewEntity;

import java.util.List;

public class productDetailDTO {
    private int productId;
    private List<String > imgages;
    private int price;
    private int total_ProductQuantity;
    private String name;
    private String categorieName;
    private int quantity;
    private String description;
    private double averageRating;
    private int totalReviews;


    public productDetailDTO() {
    }

    public productDetailDTO(int productId, List<String> imgages, int price, int total_ProductQuantity, String name, String categorieName, int quantity, String description, double averageRating, int totalReviews) {
        this.productId = productId;
        this.imgages = imgages;
        this.price = price;
        this.total_ProductQuantity = total_ProductQuantity;
        this.name = name;
        this.categorieName = categorieName;
        this.quantity = quantity;
        this.description = description;
        this.averageRating = averageRating;
        this.totalReviews = totalReviews;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public List<String> getImgages() {
        return imgages;
    }

    public void setImgages(List<String> imgages) {
        this.imgages = imgages;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal_ProductQuantity() {
        return total_ProductQuantity;
    }

    public void setTotal_ProductQuantity(int total_ProductQuantity) {
        this.total_ProductQuantity = total_ProductQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategorieName() {
        return categorieName;
    }

    public void setCategorieName(String categorieName) {
        this.categorieName = categorieName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }
}

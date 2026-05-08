package com.example.BEDACS3.Service.model.products;

public class ProductHomeDTO {
    private int productId;
    private String name;
    private String img;
    private int total_ProductQuantity;
    private String description;
    private int price;
    private int quantity;
    private int categorieId;
    private double averageRating;

    public ProductHomeDTO() {
    }

    public ProductHomeDTO(int productId, String name, String img, int total_ProductQuantity, String description, int price, int quantity, int categorieId, double averageRating) {
        this.productId = productId;
        this.name = name;
        this.img = img;
        this.total_ProductQuantity = total_ProductQuantity;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.categorieId = categorieId;
        this.averageRating = averageRating;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getTotal_ProductQuantity() {
        return total_ProductQuantity;
    }

    public void setTotal_ProductQuantity(int total_ProductQuantity) {
        this.total_ProductQuantity = total_ProductQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}

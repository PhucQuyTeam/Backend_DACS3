package com.example.BEDACS3.Service.model.products;

import java.sql.Timestamp;

public class ReviewDTO {
    private int productId;
    private String reviewerName;
    private Timestamp createdAt;
    private int rating;
    private String comment;
    private String image;

    public ReviewDTO() {
    }

    public ReviewDTO(int productId, String reviewerName, Timestamp createdAt, int rating, String comment, String image) {
        this.productId = productId;
        this.reviewerName = reviewerName;
        this.createdAt = createdAt;
        this.rating = rating;
        this.comment = comment;
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.example.BEDACS3.Repository.entity;

public class ReviewSummary {
    private int totalReviews;
    private double averageRating;

    public ReviewSummary() {
        this.totalReviews = 0;
        this.averageRating = 0.0;
    }

    public int getTotalReviews() { return totalReviews; }
    public void setTotalReviews(int totalReviews) { this.totalReviews = totalReviews; }

    public double getAverageRating() { return averageRating; }
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }
}

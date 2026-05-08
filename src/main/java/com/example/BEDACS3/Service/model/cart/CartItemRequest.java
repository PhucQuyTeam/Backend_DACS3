package com.example.BEDACS3.Service.model.cart;

public class CartItemRequest {
    private int cartId;
    private int productId;
    private int quantity;
    private double price;
    private String productName;

    // Getter & Setter
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // Getter & Setter cho tên sản phẩm
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
}
package com.example.BEDACS3.Service.model.cart;

public class CartItemDTO {
    private int cartId; // ID của dòng trong bảng cart (để sau này xóa/sửa)
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    private String productImage;

    // --- GETTER & SETTER ---
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }
}
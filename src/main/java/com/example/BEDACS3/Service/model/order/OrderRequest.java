package com.example.BEDACS3.Service.model.order;

import com.example.BEDACS3.Service.model.cart.CartItemRequest;
import java.util.List;

public class OrderRequest {
    private int addressId;
    private double totalAmount;
    private String paymentMethod;
    private List<CartItemRequest> items;

    // Getter & Setter
    public int getAddressId() { return addressId; }
    public void setAddressId(int addressId) { this.addressId = addressId; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public List<CartItemRequest> getItems() { return items; }
    public void setItems(List<CartItemRequest> items) { this.items = items; }
}
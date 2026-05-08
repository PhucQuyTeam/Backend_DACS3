package com.example.BEDACS3.Repository;

import com.example.BEDACS3.Service.model.cart.CartItemDTO;
import java.util.List;

public interface CartRepository {
    void addToCart(int userId, int productId, int quantity);
    List<CartItemDTO> getMyCart(int userId);
    void removeCartItem(int cartId);
}
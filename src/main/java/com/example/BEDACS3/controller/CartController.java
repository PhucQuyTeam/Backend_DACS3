package com.example.BEDACS3.controller;

import com.example.BEDACS3.Repository.CartRepository;
import com.example.BEDACS3.Repository.UserRepository;
import com.example.BEDACS3.Repository.impl.UserRepositoryImpl;
import com.example.BEDACS3.Service.model.cart.CartItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

     @Autowired
     private UserRepository userRepository;


    // Hàm dùng chung để lấy User ID từ Token bảo mật
    private int getCurrentUserId() {
        // 1. Lấy email/username từ Token JWT
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

         return userRepository.findByEmail(email).getId();
    }

    // API thêm sản phẩm vào giỏ
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam("productId") int productId,
                                       @RequestParam("quantity") int quantity) {
        try {
            // Lấy ID người dùng (Đã sửa lỗi)
            int userId = getCurrentUserId();

            cartRepository.addToCart(userId, productId, quantity);
            return ResponseEntity.ok("Đã thêm sản phẩm vào giỏ hàng!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
        }
    }

    // API lấy danh sách giỏ hàng
    @GetMapping("/my-cart")
    public ResponseEntity<?> getMyCart() {
        try {
            // Lấy ID người dùng (Đã sửa lỗi)
            int userId = getCurrentUserId();

            List<CartItemDTO> cart = cartRepository.getMyCart(userId);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi tải giỏ hàng: " + e.getMessage());
        }
    }
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<?> removeCartItem(@PathVariable int cartId) {
        try {
            cartRepository.removeCartItem(cartId);
            return ResponseEntity.ok("Đã xóa sản phẩm khỏi giỏ hàng");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi khi xóa sản phẩm");
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateCartQuantity(
            @RequestParam("cartId") int cartId,
            @RequestParam("quantity") int quantity) {

        // ĐÃ SỬA: Gọi từ biến userRepository (viết thường, không viết hoa tĩnh)
        boolean success = userRepository.updateCartQuantity(cartId, quantity);

        if(success) {
            return ResponseEntity.ok("Cập nhật thành công");
        } else {
            return ResponseEntity.badRequest().body("Lỗi cập nhật số lượng");
        }
    }
}

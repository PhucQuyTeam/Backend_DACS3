package com.example.BEDACS3.controller;

import com.example.BEDACS3.Repository.OrderRepository;
import com.example.BEDACS3.Service.model.order.OrderDTO;
import com.example.BEDACS3.Service.model.order.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/my-orders")
    public ResponseEntity<?> getMyOrders(@RequestParam("status") int status) {
        try {
            // 1. Lấy thông tin user đang cầm Token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            // 2. Chui xuống Repository gọi SQL
            List<OrderDTO> orders = orderRepository.findOrdersByUserAndStatus(email, status);

            // 3. Trả danh sách về cho Android
            return ResponseEntity.ok(orders);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi lấy dữ liệu đơn hàng: " + e.getMessage());
        }
    }
    // Link API: GET http://localhost:8081/order-items?orderId=70
    // Link test Postman: GET http://localhost:8081/order-items?orderId=70
    @GetMapping("/order-items")
    public ResponseEntity<?> getOrderItems(@RequestParam("orderId") int orderId) {
        try {
            List<OrderItemDTO> items = orderRepository.findOrderItemsByOrderId(orderId);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi lấy chi tiết sản phẩm: " + e.getMessage());
        }
    }
}
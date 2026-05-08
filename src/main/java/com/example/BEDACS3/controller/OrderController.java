package com.example.BEDACS3.controller;

import com.example.BEDACS3.Repository.OrderRepository;
import com.example.BEDACS3.Repository.impl.UserRepositoryImpl;
import com.example.BEDACS3.Service.model.order.OrderDTO;
import com.example.BEDACS3.Service.model.order.OrderItemDTO;
import com.example.BEDACS3.Service.model.order.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order") // BỔ SUNG: Phải có cái này thì Android gọi /api/order/create nó mới hiểu
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    @GetMapping("/my-orders")
    public ResponseEntity<?> getMyOrders(@RequestParam("status") int status) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            List<OrderDTO> orders = orderRepository.findOrdersByUserAndStatus(email, status);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi lấy dữ liệu đơn hàng: " + e.getMessage());
        }
    }

    @GetMapping("/order-items")
    public ResponseEntity<?> getOrderItems(@RequestParam("orderId") int orderId) {
        try {
            List<OrderItemDTO> items = orderRepository.findOrderItemsByOrderId(orderId);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi lấy chi tiết sản phẩm: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            int userId = userRepository.findByEmail(email).getId();

            // ĐÃ SỬA CHỖ NÀY: Dùng orderRepository thay vì userRepository
            boolean success = orderRepository.placeOrder(
                    userId,
                    request.getAddressId(),
                    request.getTotalAmount(),
                    request.getPaymentMethod(),
                    request.getItems()
            );

            if (success) {
                return ResponseEntity.ok("Đặt hàng thành công!");
            } else {
                return ResponseEntity.badRequest().body("Lỗi hệ thống khi đặt hàng");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
        }
    }
}
package com.example.BEDACS3.controller;

import com.example.BEDACS3.Repository.OrderRepository;
import com.example.BEDACS3.Repository.impl.NotificationRepositoryImpl; // THÊM IMPORT NÀY
import com.example.BEDACS3.Repository.impl.UserRepositoryImpl;
import com.example.BEDACS3.Repository.impl.reviewRepositoryImpl;
import com.example.BEDACS3.Service.model.order.OrderDTO;
import com.example.BEDACS3.Service.model.order.OrderItemDTO;
import com.example.BEDACS3.Service.model.order.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    // GỌI THẰNG ĐỆ CHUYÊN GHI THÔNG BÁO VÀO ĐÂY
    @Autowired
    private NotificationRepositoryImpl notificationRepository;
    @Autowired
    private reviewRepositoryImpl reviewRepo;

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
            // Lấy ID người dùng hiện tại
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            int userId = userRepository.findByEmail(email).getId();

            List<OrderItemDTO> items = orderRepository.findOrderItemsByOrderId(orderId);

            // QUÉT QUA TỪNG MÓN: Xem khách đã đánh giá chưa?
            for (OrderItemDTO item : items) {
                boolean reviewed = reviewRepo.hasReviewed(userId, item.getProductId(), orderId);
                item.setReviewed(reviewed);
            }

            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi lấy chi tiết sản phẩm: " + e.getMessage());
        }
    }

    // =========================================================
    // 1. THANH TOÁN TIỀN MẶT (COD)
    // =========================================================
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            int userId = userRepository.findByEmail(email).getId();

            boolean success = orderRepository.placeOrder(
                    userId,
                    request.getAddressId(),
                    request.getTotalAmount(),
                    request.getPaymentMethod(),
                    request.getItems()
            );

            if (success) {
                // ĐƠN HÀNG LƯU THÀNH CÔNG -> GHI THÔNG BÁO VÀO CSDL
                String title = "Đặt hàng thành công \uD83C\uDF89"; // \uD83C\uDF89 là icon cái pháo hoa
                String message = "Đơn hàng COD trị giá " + String.format("%,.0f", request.getTotalAmount()) + "đ đã được ghi nhận. Chúng tôi sẽ sớm giao cá cho bạn!";
                notificationRepository.insertNotification(userId, title, message);

                return ResponseEntity.ok("Đặt hàng thành công!");
            } else {
                return ResponseEntity.badRequest().body("Lỗi hệ thống khi đặt hàng");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
        }
    }

    // =========================================================
    // 2. TẠO LINK ZALOPAY
    // =========================================================
    @PostMapping("/create-zalopay")
    public ResponseEntity<?> createZaloPayOrder(@RequestBody OrderRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            int userId = userRepository.findByEmail(email).getId();

            String app_trans_id = new java.text.SimpleDateFormat("yyMMdd").format(new java.util.Date()) + "_" + System.currentTimeMillis();

            Map<String, Object> order = new HashMap<>();
            order.put("app_id", com.example.BEDACS3.Config.ZaloPayConfig.APP_ID);
            order.put("app_trans_id", app_trans_id);
            order.put("app_time", System.currentTimeMillis());
            order.put("app_user", "User_" + userId);
            order.put("amount", (long) request.getTotalAmount());
            order.put("description", "Thanh toan don hang #" + app_trans_id);
            order.put("bank_code", "");
            order.put("item", "[]");
            order.put("embed_data", "{}");
            order.put("callback_url", "https://www.google.com");

            String data = order.get("app_id") + "|" + order.get("app_trans_id") + "|" +
                    order.get("app_user") + "|" + order.get("amount") + "|" +
                    order.get("app_time") + "|" + order.get("embed_data") + "|" + order.get("item");
            String mac = com.example.BEDACS3.Config.HMACUtil.HMacHexStringEncode("HmacSHA256", com.example.BEDACS3.Config.ZaloPayConfig.KEY1, data);
            order.put("mac", mac);

            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : order.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(java.net.URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(java.net.URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(postData.toString(), headers);
            org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(com.example.BEDACS3.Config.ZaloPayConfig.CREATE_ORDER_URL, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> responseMap = mapper.readValue(response.getBody(), Map.class);
            responseMap.put("app_trans_id", app_trans_id);

            return ResponseEntity.ok(responseMap);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Lỗi ZaloPay: " + e.getMessage());
        }
    }

    // =========================================================
    // 3. KIỂM TRA ZALOPAY - LƯU ĐƠN & GHI THÔNG BÁO
    // =========================================================
    @PostMapping("/check-zalopay")
    public ResponseEntity<?> checkZaloPayStatus(
            @RequestParam("app_trans_id") String appTransId,
            @RequestBody OrderRequest request) {
        try {
            String appId = com.example.BEDACS3.Config.ZaloPayConfig.APP_ID;
            String key1 = com.example.BEDACS3.Config.ZaloPayConfig.KEY1;
            String data = appId + "|" + appTransId + "|" + key1;
            String mac = com.example.BEDACS3.Config.HMACUtil.HMacHexStringEncode("HmacSHA256", key1, data);

            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
            String postData = "app_id=" + appId + "&app_trans_id=" + appTransId + "&mac=" + mac;

            org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(postData, headers);
            org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();

            ResponseEntity<String> response = restTemplate.postForEntity("https://sb-openapi.zalopay.vn/v2/query", entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonResult = mapper.readTree(response.getBody());
            int returnCode = jsonResult.get("return_code").asInt();

            if (returnCode == 1) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String email = authentication.getName();
                int userId = userRepository.findByEmail(email).getId();

                // LƯU ĐƠN VÀO MYSQL
                boolean success = orderRepository.placeOrder(userId, request.getAddressId(), request.getTotalAmount(), "ZaloPay", request.getItems());

                // NẾU LƯU ĐƠN THÀNH CÔNG -> GHI THÔNG BÁO VÀO CSDL
                if (success) {
                    String title = "Thanh toán ZaloPay thành công \uD83D\uDCB8"; // Icon tiền
                    String message = "Đơn hàng trị giá " + String.format("%,.0f", request.getTotalAmount()) + "đ đã được thanh toán. MQ Aquatic sẽ đóng gói ngay lập tức!";
                    notificationRepository.insertNotification(userId, title, message);
                }

                return ResponseEntity.ok("Thanh toán thành công!");
            } else {
                String returnMsg = jsonResult.has("return_message") ? jsonResult.get("return_message").asText() : "Lỗi không xác định";
                return ResponseEntity.badRequest().body("ZaloPay: " + returnMsg);
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi hệ thống khi kiểm tra ZaloPay");
        }
    }
}
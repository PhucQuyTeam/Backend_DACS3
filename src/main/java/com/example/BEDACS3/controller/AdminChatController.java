package com.example.BEDACS3.controller;

import com.example.BEDACS3.Repository.UserRepository;
import com.example.BEDACS3.Service.ChatService;
import com.example.BEDACS3.Service.model.chat.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller riêng cho ADMIN PHP gọi vào.
 * Dùng API Key thay vì JWT để PHP không cần đăng nhập Spring.
 *
 * Thêm vào SecurityConfig:
 *   .requestMatchers("/api/admin/chat/**").permitAll()
 *
 * API Key: Đặt trong header  X-Admin-Key: mp_aquatic_admin_2024
 */
@RestController
@RequestMapping("/api/admin/chat")
@CrossOrigin("*")
public class AdminChatController {

    // Đổi key này thành chuỗi bí mật riêng của bạn
    private static final String ADMIN_API_KEY = "mp_aquatic_admin_2024";

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserRepository userRepository;

    /** Kiểm tra API Key từ header */
    private boolean isValidKey(String key) {
        return ADMIN_API_KEY.equals(key);
    }

//    private int getCurrentUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        return userRepository.findByEmail(email).getId();
//    }

    /**
     * GET /api/admin/chat/history?userId=42
     * PHP gọi để lấy lịch sử chat của 1 user với admin
     */
    @GetMapping("/history")
    public ResponseEntity<?> getHistory(
            @RequestHeader(value = "X-Admin-Key", required = false) String key,
            @RequestParam int userId,
            @RequestParam(defaultValue = "1") int adminId) {

        if (!isValidKey(key)) {
            return ResponseEntity.status(403).body("Forbidden: Invalid API Key");
        }

//        int userIds = getCurrentUserId();

        List<ChatDTO> history = chatService.getChatHistory(userId, adminId);
        return ResponseEntity.ok(history);
    }

    /**
     * POST /api/admin/chat/send
     * PHP gọi để admin gửi tin nhắn cho user
     *
     * Body JSON:
     * {
     *   "userId": 42,
     *   "adminId": 1,
     *   "message": "Chào bạn!",
     *   "messageType": "text"
     * }
     */
    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(
            @RequestHeader(value = "X-Admin-Key", required = false) String key,
            @RequestBody Map<String, Object> payload) {

        if (!isValidKey(key)) {
            return ResponseEntity.status(403).body("Forbidden: Invalid API Key");
        }

//        int userIds = getCurrentUserId();

        int userId      = (int) payload.get("userId");
        int adminId     = (int) payload.get("adminId");
        String message  = (String) payload.get("message");
        String msgType  = payload.getOrDefault("messageType", "text").toString();

        // Admin là sender
        chatService.processAndSaveMessage(userId, adminId, adminId, message, msgType);

        // Lấy tin nhắn vừa lưu
        List<ChatDTO> history = chatService.getChatHistory(userId, adminId);
        ChatDTO latest = history.get(history.size() - 1);

        // Bắn WebSocket tới topic mà Android đang lắng nghe
        // Android subscribe: /topic/chat/{userId}_{adminId}
        String destination = "/topic/chat/" + userId + "_" + adminId;
        messagingTemplate.convertAndSend(destination, latest);

        return ResponseEntity.ok(latest);
    }
}

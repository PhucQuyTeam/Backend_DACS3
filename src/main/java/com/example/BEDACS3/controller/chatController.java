package com.example.BEDACS3.controller;

import com.example.BEDACS3.Repository.UserRepository;
import com.example.BEDACS3.Repository.entity.BaseResponse;
import com.example.BEDACS3.Service.ChatService;
import com.example.BEDACS3.Service.model.chat.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class chatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserRepository userRepository;

    private int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).getId();
    }

    @GetMapping("/history")
    public ResponseEntity<List<ChatDTO>> getHistory(@RequestParam int adminId) {

        int userId = getCurrentUserId();

        List<ChatDTO> history = chatService.getChatHistory(userId, adminId);
        return ResponseEntity.ok(history);
    }


    @PostMapping("/send")
    public ResponseEntity<ChatDTO> sendMessage(@RequestBody Map<String, Object> payload) {

            // 1. Lấy ID người dùng từ Token bảo mật
            int currentUserId = getCurrentUserId();

            // Vì đây là luồng của Khách Hàng gửi tin, nên userId của phòng chat và senderId chính là họ.
            int userId = currentUserId;
            int senderId = currentUserId;

            // 2. Parse các dữ liệu khác từ request body
            int adminId = (int) payload.get("adminId");
            String message = (String) payload.get("message");
            String messageType = (String) payload.getOrDefault("messageType", "text");

            // 3. Lưu tin nhắn vào Database (Tạo phòng chat nếu chưa có)
            chatService.processAndSaveMessage(userId, adminId, senderId, message, messageType);

            // 4. Lấy ra tin nhắn vừa lưu (đã được gán Name và Avatar từ DTO)
            List<ChatDTO> history = chatService.getChatHistory(userId, adminId);
            ChatDTO latestMessage = history.get(history.size() - 1); // Lấy tin cuối cùng

            // 5. BẮN QUA WEBSOCKET
            String destination = "/topic/chat/" + userId + "_" + adminId;
            messagingTemplate.convertAndSend(destination, latestMessage);

            // 6. Trả về response
            return ResponseEntity.ok(latestMessage);


    }

    @GetMapping("/unread-count")
    public ResponseEntity<Integer> getUnreadMessageCount() {
        try {
            // Lấy ID người dùng từ Token bảo mật
            int userId = getCurrentUserId();

            // Gọi Service để đếm
            int count = chatService.getUnreadMessageCount(userId);

            return ResponseEntity.ok(count);
        } catch (Exception e) {
            // Nếu có lỗi (ví dụ chưa đăng nhập), trả về 0 để ẩn huy hiệu
            return ResponseEntity.badRequest().body(0);
        }
    }

    @PutMapping("/mark-read")
    public ResponseEntity<?> markAsRead(@RequestParam int adminId) {
        try {
            int userId = getCurrentUserId();
            chatService.markChatAsRead(userId, adminId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/upload-image")
    public BaseResponse uploadChatImage(@RequestParam("image") MultipartFile file) {
        try {
            // 1. Tạo tên file duy nhất (tránh trùng lặp)
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // 2. Trỏ tới thư mục "upload" ở thư mục gốc của project (Khớp với PhotoConfig)
            Path uploadPath = Paths.get("upload");

            // 3. Nếu thư mục upload chưa tồn tại thì tự động tạo mới
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 4. Lưu file ảnh vào thư mục upload
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 5. Trả về tên file cho điện thoại Android
            return new BaseResponse(true, fileName);

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse(false, "Lỗi khi lưu ảnh: " + e.getMessage());
        }
    }

}

package com.example.BEDACS3.Service;

import com.example.BEDACS3.Repository.*;
import com.example.BEDACS3.Repository.entity.productAiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Autowired
    private productRepository repository;

    // ĐIỀN API KEY CỦA BẠN VÀO ĐÂY
    private final String GEMINI_API_KEY = "AIzaSyBzPIWO6FuzleRNK_i9xJQDDJe4ScVx7j8";
    // Cập nhật lên model gemini-2.5-flash mới nhất của Google
    private final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + GEMINI_API_KEY;

    public String askAI(String userMessage) {
        List<productAiEntity> listProducts = repository.getAllProducts();

        StringBuilder shopData = new StringBuilder();
        for (productAiEntity p : listProducts) {
            String catName = p.getCategoryName() != null ? p.getCategoryName() : "Khác";
            String description = p.getDescription() != null ? p.getDescription() : "Đang cập nhật";

            shopData.append("- Tên sản phẩm: ").append(p.getName()).append("\n")
                    .append("  + Phân loại: ").append(catName).append("\n")
                    .append("  + Giá bán: ").append(p.getPrice()).append(" VNĐ\n")
                    .append("  + Tồn kho: ").append(p.getQuantity()).append(" sản phẩm\n")
                    .append("  + Mô tả chi tiết: ").append(description).append("\n\n");
        }

        // 3. TẠO PROMPT "THAO TÚNG TÂM LÝ" SIÊU CHUẨN
        String systemPrompt = "Bạn là nhân viên tư vấn cực kỳ chuyên nghiệp và thân thiện của cửa hàng PQ Aquarium Shop. " +
                "Nhiệm vụ của bạn là tư vấn cho khách hàng dựa vào DANH SÁCH THÔNG TIN SẢN PHẨM HIỆN CÓ dưới đây.\n\n" +
                "QUY TẮC BẮT BUỘC (PHẢI TUÂN THỦ 100%):\n" +
                "1. TUYỆT ĐỐI KHÔNG bịa ra sản phẩm, giá cả, hoặc công dụng không có trong danh sách.\n" +
                "2. Nếu khách hỏi chi tiết công dụng/cách dùng, hãy tìm trong phần 'Mô tả chi tiết' để trả lời.\n" +
                "3. Chú ý 'Tồn kho': Nếu sản phẩm tồn kho = 0, phải báo khách là hết hàng và khéo léo gợi ý sản phẩm khác cùng 'Phân loại'.\n" +
                "4. Nếu khách hỏi sản phẩm mà shop không có, hãy xin lỗi và giới thiệu các sản phẩm shop đang bán.\n" +
                "5. Trả lời ngắn gọn, súc tích, xuống dòng rõ ràng và xưng hô thân thiện (Shop - Bạn).\n\n" +
                "--- BẮT ĐẦU DANH SÁCH SẢN PHẨM ---\n" +
                shopData.toString() +
                "--- KẾT THÚC DANH SÁCH ---\n\n" +
                "Khách hàng vừa nhắn: '" + userMessage + "'\n" +
                "Hãy đưa ra câu trả lời của bạn:";

        // 4. ĐÓNG GÓI REQUEST GỬI LÊN GOOGLE
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Build cục JSON theo đúng chuẩn của Google Gemini API
        Map<String, Object> parts = new HashMap<>();
        parts.put("text", systemPrompt);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", new Object[]{parts});

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", new Object[]{content});

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            // 5. BẮN API
            ResponseEntity<Map> response = restTemplate.postForEntity(GEMINI_API_URL, request, Map.class);

            // 6. BÓC TÁCH KẾT QUẢ TRẢ VỀ (Bóc qua nhiều lớp JSON của Google)
            Map<String, Object> body = response.getBody();
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) body.get("candidates");
            Map<String, Object> contentRes = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> partsRes = (List<Map<String, Object>>) contentRes.get("parts");

            return (String) partsRes.get(0).get("text");

        } catch (Exception e) {
            e.printStackTrace();
            return "Xin lỗi, hệ thống AI đang bảo trì. Bạn vui lòng liên hệ hotline nhé!";
        }
    }
}
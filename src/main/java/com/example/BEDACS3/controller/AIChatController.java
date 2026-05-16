package com.example.BEDACS3.controller;

import com.example.BEDACS3.Service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ai-chat")
@CrossOrigin("*")
public class AIChatController {
    @Autowired
    private GeminiService geminiService;

    @PostMapping("/ask")
    public ResponseEntity<Map<String, String>> askBot(@RequestBody Map<String, String> request) {
        String userMsg = request.get("message");

        String aiResponse = geminiService.askAI(userMsg);

        Map<String, String> result = new HashMap<>();
        result.put("reply", aiResponse);

        return ResponseEntity.ok(result);
    }
}

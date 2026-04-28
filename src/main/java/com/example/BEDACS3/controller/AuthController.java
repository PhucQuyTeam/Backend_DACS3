package com.example.BEDACS3.controller;

import com.example.BEDACS3.Service.impl.AuthServiceImpl;
import com.example.BEDACS3.Service.model.auth.AuthResponse;
import com.example.BEDACS3.Service.model.auth.LoginRequest;
import com.example.BEDACS3.Service.model.auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService; // Gọi xuống tầng Service

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}
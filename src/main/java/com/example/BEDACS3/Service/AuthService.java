package com.example.BEDACS3.Service;

import com.example.BEDACS3.Service.model.auth.AuthResponse;
import com.example.BEDACS3.Service.model.auth.LoginRequest;
import com.example.BEDACS3.Service.model.auth.RefreshTokenRequest;
import com.example.BEDACS3.Service.model.auth.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
}
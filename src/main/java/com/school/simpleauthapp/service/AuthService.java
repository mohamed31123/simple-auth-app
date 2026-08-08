package com.school.simpleauthapp.service;

import com.school.simpleauthapp.dto.request.LoginRequest;
import com.school.simpleauthapp.dto.request.RegisterRequest;
import com.school.simpleauthapp.dto.response.AuthResponse;
import org.springframework.stereotype.Service;


@Service
public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}

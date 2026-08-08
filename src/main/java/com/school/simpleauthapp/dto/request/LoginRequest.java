package com.school.simpleauthapp.dto.request;

public record LoginRequest(
        String username,
        String password
) {}

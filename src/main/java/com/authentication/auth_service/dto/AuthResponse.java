package com.authentication.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private String email;
    private UUID id; // ✅ Added userId field

    // Optional convenience constructor without UUID
    public AuthResponse(String message, String email) {
        this.message = message;
        this.email = email;
    }
}

package com.authentication.auth_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private String email;

    public AuthResponse(String s) {
    }
}

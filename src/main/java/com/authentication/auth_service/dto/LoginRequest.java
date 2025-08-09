package com.authentication.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor  // ✅ This creates LoginRequest(String email, String password)
public class LoginRequest {
    private String email;
    private String password;
}

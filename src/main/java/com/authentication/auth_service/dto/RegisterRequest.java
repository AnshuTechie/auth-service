package com.authentication.auth_service.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
    private String role; // "USER", "OWNER", "ADMIN"
}

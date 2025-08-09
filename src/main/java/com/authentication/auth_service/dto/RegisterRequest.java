package com.authentication.auth_service.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String username;
    private String password;

    public RegisterRequest(String mail, String pass123, String testUser, String number, String user) {
    }
}

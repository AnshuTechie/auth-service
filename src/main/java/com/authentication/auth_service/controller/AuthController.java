package com.authentication.auth_service.controller;

//package com.authservice.controller;
//
//import com.authservice.dto.RegisterRequest;
//import com.authservice.dto.AuthResponse;
//import com.authservice.service.AuthService;
import com.authentication.auth_service.dto.AuthResponse;
import com.authentication.auth_service.dto.RegisterRequest;
import com.authentication.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}

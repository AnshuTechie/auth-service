package com.authentication.auth_service.controller;

import com.authentication.auth_service.dto.AuthResponse;
import com.authentication.auth_service.dto.LoginRequest;
import com.authentication.auth_service.dto.LoginResponse;
import com.authentication.auth_service.dto.RegisterRequest;
import com.authentication.auth_service.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     *  Register a new user (USER, OWNER, ADMIN)
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    /**
     *  Authenticate user and return JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     *    View profile for logged-in user (JWT required)
     *     Extracts userId from request set in JwtAuthFilter
     */
    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(Authentication authentication, HttpServletRequest request) {
        UUID userId = (UUID) request.getAttribute("userId");
        String username = authentication.getName();
        return ResponseEntity.ok("Profile Info → ID: " + userId + ", Username: " + username);
    }

    /**
     *  Test endpoint for ADMIN role
     */
    @GetMapping("/admin/dashboard")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Welcome Admin! You have full access.");
    }

    /**
     * Test endpoint for OWNER role
     */
    @GetMapping("/owner/dashboard")
    public ResponseEntity<String> ownerDashboard() {
        return ResponseEntity.ok("Welcome Owner! You can manage your resources.");
    }

    /**
     * Test endpoint for USER role
     */
    @GetMapping("/user/dashboard")
    public ResponseEntity<String> userDashboard() {
        return ResponseEntity.ok("Welcome User! You can access your account.");
    }
}

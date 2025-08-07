package com.authentication.auth_service.service;

import com.authentication.auth_service.dto.AuthResponse;
import com.authentication.auth_service.dto.LoginRequest;
import com.authentication.auth_service.dto.LoginResponse;
import com.authentication.auth_service.dto.RegisterRequest;
import com.authentication.auth_service.exception.EmailAlreadyExistsException;
import com.authentication.auth_service.model.Role;
import com.authentication.auth_service.model.User;
import com.authentication.auth_service.repository.UserRepository;
import com.authentication.auth_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public AuthResponse register(RegisterRequest request) {

        // ✅ Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already registered");
        }

        // ✅ Hash the password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // ✅ Build user object
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(hashedPassword)
                .role(Role.USER) // default role
                .build();

        // ✅ Save to DB
        userRepository.save(user);

        // ✅ Return response
        return new AuthResponse("User registered successfully", user.getEmail());
    }
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String jwt = jwtUtil.generateToken(user);

        return new LoginResponse(jwt, user.getEmail(), user.getRole().name());
    }

}

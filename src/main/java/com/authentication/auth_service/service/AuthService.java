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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * ✅ Register a new user with specified role
     */
    public AuthResponse register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already registered");
        }

        // Validate role input
        Role role = parseRole(request.getRole());

        // Encode password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Create and save new user
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(hashedPassword)
                .role(role)
                .build();

        userRepository.save(user);

        return new AuthResponse("User registered successfully", user.getEmail());
    }

    /**
     * ✅ Login and return JWT if credentials are valid
     */
    public LoginResponse login(LoginRequest request) {

        // Fetch user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Validate password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT
        String jwt = jwtUtil.generateToken(user);

        return new LoginResponse(jwt, user.getEmail(), user.getRole().name());
    }

    /**
     * ✅ Fetch user details by email (used by JwtAuthFilter)
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    /**
     * ✅ Parse and validate role string from request
     */
    private Role parseRole(String roleInput) {
        if (roleInput == null || roleInput.isBlank()) {
            throw new IllegalArgumentException("Role must not be empty. Allowed values: USER, OWNER, ADMIN");
        }
        try {
            return Role.valueOf(roleInput.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role. Allowed values: USER, OWNER, ADMIN");
        }
    }
}




//package com.authentication.auth_service.service;
//
//import com.authentication.auth_service.dto.AuthResponse;
//import com.authentication.auth_service.dto.LoginRequest;
//import com.authentication.auth_service.dto.LoginResponse;
//import com.authentication.auth_service.dto.RegisterRequest;
//import com.authentication.auth_service.exception.EmailAlreadyExistsException;
//import com.authentication.auth_service.model.Role;
//import com.authentication.auth_service.model.User;
//import com.authentication.auth_service.repository.UserRepository;
//import com.authentication.auth_service.util.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//
//    /**
//     * ✅ Register a new user with role USER, OWNER, or ADMIN
//     */
//    public AuthResponse register(RegisterRequest request) {
//
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            throw new EmailAlreadyExistsException("Email is already registered");
//        }
//
//        // Validate and parse role from request
//        Role role;
//        try {
//            role = Role.valueOf(request.getRole().toUpperCase());
//        } catch (IllegalArgumentException | NullPointerException e) {
//            throw new IllegalArgumentException("Invalid role. Allowed values: USER, OWNER, ADMIN");
//        }
//
//        String hashedPassword = passwordEncoder.encode(request.getPassword());
//
//        User user = User.builder()
//                .email(request.getEmail())
//                .username(request.getUsername())
//                .password(hashedPassword)
//                .role(role)
//                .build();
//
//        userRepository.save(user);
//
//        return new AuthResponse("User registered successfully", user.getEmail());
//    }
//
//    /**
//     * ✅ Login and return JWT if credentials are valid
//     */
//    public LoginResponse login(LoginRequest request) {
//
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }
//
//        String jwt = jwtUtil.generateToken(user);
//
//        return new LoginResponse(jwt, user.getEmail(), user.getRole().name());
//    }
//
//    /**
//     * ✅ Used by JwtAuthFilter to fetch User by email (from token)
//     */
//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//    }
//}






//package com.authentication.auth_service.service;
//
//import com.authentication.auth_service.dto.AuthResponse;
//import com.authentication.auth_service.dto.LoginRequest;
//import com.authentication.auth_service.dto.LoginResponse;
//import com.authentication.auth_service.dto.RegisterRequest;
//import com.authentication.auth_service.exception.EmailAlreadyExistsException;
//import com.authentication.auth_service.model.Role;
//import com.authentication.auth_service.model.User;
//import com.authentication.auth_service.repository.UserRepository;
//import com.authentication.auth_service.util.JwtUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//
//    /**
//     * ✅ Register a new user
//     */
//    public AuthResponse register(RegisterRequest request) {
//
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            throw new EmailAlreadyExistsException("Email is already registered");
//        }
//
//        String hashedPassword = passwordEncoder.encode(request.getPassword());
//
//        User user = User.builder()
//                .email(request.getEmail())
//                .username(request.getUsername())
//                .password(hashedPassword)
//                .role(Role.USER)
//                .build();
//
//        userRepository.save(user);
//
//        return new AuthResponse("User registered successfully", user.getEmail());
//    }
//
//    /**
//     * ✅ Login and return JWT if credentials are valid
//     */
//    public LoginResponse login(LoginRequest request) {
//
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }
//
//        String jwt = jwtUtil.generateToken(user);
//
//        return new LoginResponse(jwt, user.getEmail(), user.getRole().name());
//    }
//
//    /**
//     * ✅ Used by JwtAuthFilter to fetch User by email (from token)
//     */
//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//    }
//}

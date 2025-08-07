package com.authentication.auth_service.service;

import com.authentication.auth_service.dto.AuthResponse;
import com.authentication.auth_service.dto.RegisterRequest;
import com.authentication.auth_service.exception.EmailAlreadyExistsException;
import com.authentication.auth_service.model.Role;
import com.authentication.auth_service.model.User;
import com.authentication.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}



/*package com.authentication.auth_service.service;



//import com.authentication.auth_service.service.dto.AuthResponse;
//import com.authservice.dto.RegisterRequest;
//import com.authservice.model.Role;
//import com.authservice.model.User;
//import com.authservice.repository.UserRepository;
import com.authentication.auth_service.dto.AuthResponse;
import com.authentication.auth_service.dto.RegisterRequest;
import com.authentication.auth_service.model.Role;
import com.authentication.auth_service.model.User;
import com.authentication.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        // Check if user already exists
        userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new RuntimeException("User already exists");
        });

        // Hash the password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Build user object
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(hashedPassword)
                .role(Role.USER) // default role
                .build();

        // Save to DB
        userRepository.save(user);

        return new AuthResponse("User registered successfully", user.getEmail());
    }
}
*/
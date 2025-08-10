package com.authentication.auth_service.servicetest;

import com.authentication.auth_service.dto.AuthResponse;
import com.authentication.auth_service.dto.LoginRequest;
import com.authentication.auth_service.dto.LoginResponse;
import com.authentication.auth_service.dto.RegisterRequest;
import com.authentication.auth_service.exception.EmailAlreadyExistsException;
import com.authentication.auth_service.model.Role;
import com.authentication.auth_service.model.User;
import com.authentication.auth_service.repository.UserRepository;
import com.authentication.auth_service.service.AuthService;
import com.authentication.auth_service.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Test Register: success with role
    @Test
    void register_ShouldSaveUser_WhenEmailNotExists_AndRoleValid() {
        RegisterRequest request = new RegisterRequest("test@example.com", "TestUser", "pass123", "USER");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("hashedPassword");

        AuthResponse response = authService.register(request);

        assertEquals("User registered successfully", response.getMessage());
        assertEquals("test@example.com", response.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    // ✅ Test Register: invalid role
    @Test
    void register_ShouldThrowException_WhenRoleInvalid() {
        RegisterRequest request = new RegisterRequest("test@example.com", "TestUser", "pass123", "INVALID");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> authService.register(request));
        assertTrue(ex.getMessage().contains("Invalid role"));
    }

    // ✅ Test Register: email exists
    @Test
    void register_ShouldThrowException_WhenEmailExists() {
        RegisterRequest request = new RegisterRequest("test@example.com", "TestUser", "pass123", "USER");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(EmailAlreadyExistsException.class, () -> authService.register(request));
        verify(userRepository, never()).save(any(User.class));
    }

    // ✅ Test Login: success
    @Test
    void login_ShouldReturnJwt_WhenCredentialsValid() {
        LoginRequest request = new LoginRequest("test@example.com", "pass123");
        User user = User.builder()
                .email("test@example.com")
                .password("hashedPassword")
                .role(Role.USER)
                .build();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(user)).thenReturn("mockJwtToken");

        LoginResponse response = authService.login(request);

        assertEquals("mockJwtToken", response.getToken());
        assertEquals("test@example.com", response.getEmail());
        assertEquals("USER", response.getRole());
    }

    // ✅ Test Login: user not found
    @Test
    void login_ShouldThrowException_WhenUserNotFound() {
        LoginRequest request = new LoginRequest("notfound@example.com", "pass123");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> authService.login(request));
    }

    // ✅ Test Login: invalid password
    @Test
    void login_ShouldThrowException_WhenInvalidPassword() {
        LoginRequest request = new LoginRequest("test@example.com", "wrongPass");
        User user = User.builder()
                .email("test@example.com")
                .password("hashedPassword")
                .role(Role.USER)
                .build();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(RuntimeException.class, () -> authService.login(request));
    }

    // ✅ Test getUserByEmail: success
    @Test
    void getUserByEmail_ShouldReturnUser_WhenFound() {
        User user = User.builder()
                .email("test@example.com")
                .password("hashedPassword")
                .role(Role.USER)
                .build();

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User result = authService.getUserByEmail("test@example.com");

        assertEquals("test@example.com", result.getEmail());
    }

    // ✅ Test getUserByEmail: not found
    @Test
    void getUserByEmail_ShouldThrowException_WhenNotFound() {
        when(userRepository.findByEmail("missing@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> authService.getUserByEmail("missing@example.com"));
    }
}







//package com.authentication.auth_service.servicetest;
//
//import com.authentication.auth_service.dto.AuthResponse;
//import com.authentication.auth_service.dto.LoginRequest;
//import com.authentication.auth_service.dto.LoginResponse;
//import com.authentication.auth_service.dto.RegisterRequest;
//import com.authentication.auth_service.exception.EmailAlreadyExistsException;
//import com.authentication.auth_service.model.Role;
//import com.authentication.auth_service.model.User;
//import com.authentication.auth_service.repository.UserRepository;
//import com.authentication.auth_service.service.AuthService;
//import com.authentication.auth_service.util.JwtUtil;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class AuthServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Mock
//    private JwtUtil jwtUtil;
//
//    @InjectMocks
//    private AuthService authService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    // ✅ Test Register: success
//    @Test
//    void register_ShouldSaveUser_WhenEmailNotExists() {
//        RegisterRequest request = new RegisterRequest("test@example.com", "TestUser", "pass123");
//
//        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
//        when(passwordEncoder.encode(request.getPassword())).thenReturn("hashedPassword");
//
//        AuthResponse response = authService.register(request);
//
//        assertEquals("User registered successfully", response.getMessage());
//        assertEquals("test@example.com", response.getEmail());
//        verify(userRepository, times(1)).save(any(User.class));
//    }
//
//    // ✅ Test Register: email exists
//    @Test
//    void register_ShouldThrowException_WhenEmailExists() {
//        RegisterRequest request = new RegisterRequest("test@example.com", "TestUser", "pass123");
//
//        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new User()));
//
//        assertThrows(EmailAlreadyExistsException.class, () -> authService.register(request));
//        verify(userRepository, never()).save(any(User.class));
//    }
//
//    // ✅ Test Login: success
//    @Test
//    void login_ShouldReturnJwt_WhenCredentialsValid() {
//        LoginRequest request = new LoginRequest("test@example.com", "pass123");
//        User user = User.builder()
//                .email("test@example.com")
//                .password("hashedPassword")
//                .role(Role.USER)
//                .build();
//
//        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);
//        when(jwtUtil.generateToken(user)).thenReturn("mockJwtToken");
//
//        LoginResponse response = authService.login(request);
//
//        assertEquals("mockJwtToken", response.getToken());
//        assertEquals("test@example.com", response.getEmail());
//        assertEquals("USER", response.getRole());
//    }
//
//    // ✅ Test Login: user not found
//    @Test
//    void login_ShouldThrowException_WhenUserNotFound() {
//        LoginRequest request = new LoginRequest("notfound@example.com", "pass123");
//
//        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
//
//        assertThrows(UsernameNotFoundException.class, () -> authService.login(request));
//    }
//
//    // ✅ Test Login: invalid password
//    @Test
//    void login_ShouldThrowException_WhenInvalidPassword() {
//        LoginRequest request = new LoginRequest("test@example.com", "wrongPass");
//        User user = User.builder()
//                .email("test@example.com")
//                .password("hashedPassword")
//                .role(Role.USER)
//                .build();
//
//        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(false);
//
//        assertThrows(RuntimeException.class, () -> authService.login(request));
//    }
//
//    // ✅ Test getUserByEmail: success
//    @Test
//    void getUserByEmail_ShouldReturnUser_WhenFound() {
//        User user = User.builder()
//                .email("test@example.com")
//                .password("hashedPassword")
//                .role(Role.USER)
//                .build();
//
//        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
//
//        User result = authService.getUserByEmail("test@example.com");
//
//        assertEquals("test@example.com", result.getEmail());
//    }
//
//    // ✅ Test getUserByEmail: not found
//    @Test
//    void getUserByEmail_ShouldThrowException_WhenNotFound() {
//        when(userRepository.findByEmail("missing@example.com")).thenReturn(Optional.empty());
//
//        assertThrows(UsernameNotFoundException.class, () -> authService.getUserByEmail("missing@example.com"));
//    }
//}

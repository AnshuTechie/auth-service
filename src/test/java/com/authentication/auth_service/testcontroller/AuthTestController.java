package com.authentication.auth_service.testcontroller;

import com.authentication.auth_service.controller.AuthController;
import com.authentication.auth_service.dto.AuthResponse;
import com.authentication.auth_service.dto.LoginRequest;
import com.authentication.auth_service.dto.LoginResponse;
import com.authentication.auth_service.dto.RegisterRequest;
import com.authentication.auth_service.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(AuthControllerTest.MockConfig.class)
class AuthControllerTest {

    @Configuration
    static class MockConfig {
        @Bean
        public AuthService authService() {
            return Mockito.mock(AuthService.class);
        }
    }

    @Autowired
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testRegister() throws Exception {
        RegisterRequest request = new RegisterRequest(
                "test@example.com",
                "TestUser",
                "pass123",
                "USER" // ✅ role is now required
        );

        AuthResponse response = new AuthResponse("User registered successfully", "test@example.com");

        when(authService.register(any(RegisterRequest.class))).thenReturn(response);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User registered successfully"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void testLogin() throws Exception {
        LoginRequest request = new LoginRequest("test@example.com", "pass123");
        LoginResponse response = new LoginResponse("jwt-token", "test@example.com", "USER");

        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    void testGetProfile() throws Exception {
        Authentication mockAuth = mock(Authentication.class);
        when(mockAuth.getName()).thenReturn("test@example.com");

        mockMvc.perform(get("/auth/profile")
                        .principal(mockAuth))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, test@example.com! This is your profile."));
    }
}







//package com.authentication.auth_service.testcontroller;
//
//import com.authentication.auth_service.controller.AuthController;
//import com.authentication.auth_service.dto.AuthResponse;
//import com.authentication.auth_service.dto.LoginRequest;
//import com.authentication.auth_service.dto.LoginResponse;
//import com.authentication.auth_service.dto.RegisterRequest;
//import com.authentication.auth_service.service.AuthService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(AuthController.class)
//@Import(AuthControllerTest.MockConfig.class) // Import mock config
//class AuthControllerTest {
//
//    @Configuration
//    static class MockConfig {
//        @Bean
//        public AuthService authService() {
//            return Mockito.mock(AuthService.class);
//        }
//    }
//
//    @Autowired
//    private AuthService authService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void testRegister() throws Exception {
//        RegisterRequest request = new RegisterRequest("test@example.com", "TestUser", "pass123");
//        AuthResponse response = new AuthResponse("User registered successfully", "test@example.com");
//
//        when(authService.register(any(RegisterRequest.class))).thenReturn(response);
//
//        mockMvc.perform(post("/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("User registered successfully"))
//                .andExpect(jsonPath("$.email").value("test@example.com"));
//    }
//
//    @Test
//    void testLogin() throws Exception {
//        LoginRequest request = new LoginRequest("test@example.com", "pass123");
//        LoginResponse response = new LoginResponse("jwt-token", "test@example.com", "USER");
//
//        when(authService.login(any(LoginRequest.class))).thenReturn(response);
//
//        mockMvc.perform(post("/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").value("jwt-token"))
//                .andExpect(jsonPath("$.email").value("test@example.com"))
//                .andExpect(jsonPath("$.role").value("USER"));
//    }
//
//    @Test
//    void testGetProfile() throws Exception {
//        Authentication mockAuth = mock(Authentication.class);
//        when(mockAuth.getName()).thenReturn("test@example.com");
//
//        mockMvc.perform(get("/auth/profile")
//                        .principal(mockAuth)) // directly injects Authentication
//                .andExpect(status().isOk())
//                .andExpect(content().string("Hello, test@example.com! This is your profile."));
//    }
//}

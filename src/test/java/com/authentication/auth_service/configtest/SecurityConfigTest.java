package com.authentication.auth_service.configtest;

import com.authentication.auth_service.config.SecurityConfig;
import com.authentication.auth_service.filter.JwtAuthFilter;
import com.authentication.auth_service.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;


    @Test
    void passwordEncoderBeanExists() {
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        assertThat(encoder).isNotNull();
        assertThat(encoder.encode("password")).isNotBlank();
    }

    @Test
    void authenticationManagerBeanExists() throws Exception {
        assertThat(context.getBean("authenticationManager")).isNotNull();
    }

    @Test
    void whenAccessPublicEndpoint_thenOk() throws Exception {
        mockMvc.perform(get("/auth/register") // even though it's GET, just testing security rules
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // should be accessible without authentication
    }

    @Test
    void whenAccessAdminEndpointWithoutAuth_thenForbidden() throws Exception {
        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isForbidden());
    }

    @Test
    void whenAccessOwnerEndpointWithoutAuth_thenForbidden() throws Exception {
        mockMvc.perform(get("/owner/data"))
                .andExpect(status().isForbidden());
    }
}

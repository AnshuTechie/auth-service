//package com.authentication.auth_service.dtotest;
//
//import com.authentication.auth_service.dto.LoginResponse;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class LoginResponseTest {
//
//    @Test
//    void allArgsConstructorShouldSetFieldsCorrectly() {
//        LoginResponse response = new LoginResponse("jwt-token-123", "user@example.com", "ROLE_USER");
//
//        assertThat(response.getToken()).isEqualTo("jwt-token-123");
//        assertThat(response.getEmail()).isEqualTo("user@example.com");
//        assertThat(response.getRole()).isEqualTo("ROLE_USER");
//    }
//
//    @Test
//    void settersAndGettersShouldWork() {
//        LoginResponse response = new LoginResponse(null, null, null);
//        response.setToken("jwt-token-123");
//        response.setEmail("user@example.com");
//        response.setRole("ROLE_USER");
//
//        assertThat(response.getToken()).isEqualTo("jwt-token-123");
//        assertThat(response.getEmail()).isEqualTo("user@example.com");
//        assertThat(response.getRole()).isEqualTo("ROLE_USER");
//    }
//
//    @Test
//    void equalsAndHashCodeShouldBeBasedOnFields() {
//        LoginResponse r1 = new LoginResponse("token123", "user@example.com", "ROLE_USER");
//        LoginResponse r2 = new LoginResponse("token123", "user@example.com", "ROLE_USER");
//
//        assertThat(r1).isEqualTo(r2);
//        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
//    }
//
//    @Test
//    void toStringShouldContainFieldValues() {
//        LoginResponse response = new LoginResponse("jwt-token-123", "user@example.com", "ROLE_USER");
//
//        String toStringResult = response.toString();
//
//        assertThat(toStringResult).contains("jwt-token-123");
//        assertThat(toStringResult).contains("user@example.com");
//        assertThat(toStringResult).contains("ROLE_USER");
//    }
//}

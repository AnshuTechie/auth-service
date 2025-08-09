//package com.authentication.auth_service.dtotest;
//
//import com.authentication.auth_service.dto.LoginRequest;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class LoginRequestTest {
//
//    @Test
//    void allArgsConstructorShouldSetFieldsCorrectly() {
//        LoginRequest request = new LoginRequest("test@example.com", "pass123");
//
//        assertThat(request.getEmail()).isEqualTo("test@example.com");
//        assertThat(request.getPassword()).isEqualTo("pass123");
//    }
//
//    @Test
//    void settersAndGettersShouldWork() {
//        LoginRequest request = new LoginRequest();
//        request.setEmail("test@example.com");
//        request.setPassword("pass123");
//
//        assertThat(request.getEmail()).isEqualTo("test@example.com");
//        assertThat(request.getPassword()).isEqualTo("pass123");
//    }
//
//    @Test
//    void equalsAndHashCodeShouldBeBasedOnFields() {
//        LoginRequest r1 = new LoginRequest("test@example.com", "pass123");
//        LoginRequest r2 = new LoginRequest("test@example.com", "pass123");
//
//        assertThat(r1).isEqualTo(r2);
//        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
//    }
//
//    @Test
//    void toStringShouldContainFieldValues() {
//        LoginRequest request = new LoginRequest("test@example.com", "pass123");
//
//        String toStringResult = request.toString();
//
//        assertThat(toStringResult).contains("test@example.com");
//        assertThat(toStringResult).contains("pass123");
//    }
//}

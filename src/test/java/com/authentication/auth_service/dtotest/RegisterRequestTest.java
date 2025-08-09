//package com.authentication.auth_service.dtotest;
//
//import com.authentication.auth_service.dto.RegisterRequest;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class RegisterRequestTest {
//
//    @Test
//    void allArgsConstructorShouldSetFieldsCorrectly() {
//        RegisterRequest request = new RegisterRequest("user@example.com", "testUser", "pass123");
//
//        assertThat(request.getEmail()).isEqualTo("user@example.com");
//        assertThat(request.getUsername()).isEqualTo("testUser");
//        assertThat(request.getPassword()).isEqualTo("pass123");
//    }
//
//    @Test
//    void settersAndGettersShouldWork() {
//        RegisterRequest request = new RegisterRequest();
//        request.setEmail("user@example.com");
//        request.setUsername("testUser");
//        request.setPassword("pass123");
//
//        assertThat(request.getEmail()).isEqualTo("user@example.com");
//        assertThat(request.getUsername()).isEqualTo("testUser");
//        assertThat(request.getPassword()).isEqualTo("pass123");
//    }
//
//    @Test
//    void equalsAndHashCodeShouldBeBasedOnFields() {
//        RegisterRequest r1 = new RegisterRequest("user@example.com", "testUser", "pass123");
//        RegisterRequest r2 = new RegisterRequest("user@example.com", "testUser", "pass123");
//
//        assertThat(r1).isEqualTo(r2);
//        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
//    }
//
//    @Test
//    void toStringShouldContainFieldValues() {
//        RegisterRequest request = new RegisterRequest("user@example.com", "testUser", "pass123");
//
//        String toStringResult = request.toString();
//
//        assertThat(toStringResult).contains("user@example.com");
//        assertThat(toStringResult).contains("testUser");
//        assertThat(toStringResult).contains("pass123");
//    }
//}

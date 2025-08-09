//package com.authentication.auth_service.dtotest;
//
//import com.authentication.auth_service.dto.AuthResponse;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class AuthResponseTest {
//
//    @Test
//    void allArgsConstructorShouldSetFieldsCorrectly() {
//        AuthResponse response = new AuthResponse("Success", "test@example.com");
//
//        assertThat(response.getMessage()).isEqualTo("Success");
//        assertThat(response.getEmail()).isEqualTo("test@example.com");
//    }
//
//    @Test
//    void settersAndGettersShouldWork() {
//        AuthResponse response = new AuthResponse();
//        response.setMessage("Success");
//        response.setEmail("test@example.com");
//
//        assertThat(response.getMessage()).isEqualTo("Success");
//        assertThat(response.getEmail()).isEqualTo("test@example.com");
//    }
//
//    @Test
//    void equalsAndHashCodeShouldBeBasedOnFields() {
//        AuthResponse r1 = new AuthResponse("Success", "test@example.com");
//        AuthResponse r2 = new AuthResponse("Success", "test@example.com");
//
//        assertThat(r1).isEqualTo(r2);
//        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
//    }
//
//    @Test
//    void toStringShouldContainFieldValues() {
//        AuthResponse response = new AuthResponse("Success", "test@example.com");
//
//        String toStringResult = response.toString();
//
//        assertThat(toStringResult).contains("Success");
//        assertThat(toStringResult).contains("test@example.com");
//    }
//}

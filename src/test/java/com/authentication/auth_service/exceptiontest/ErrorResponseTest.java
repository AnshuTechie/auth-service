//package com.authentication.auth_service.exceptiontest;
//
//import com.authentication.auth_service.exception.ErrorResponse;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class ErrorResponseTest {
//
//    @Test
//    void allArgsConstructorShouldSetAllFields() {
//        LocalDateTime fixedTime = LocalDateTime.of(2025, 8, 9, 10, 30, 0);
//        ErrorResponse errorResponse = new ErrorResponse(404, "Not Found", fixedTime, "/test/path");
//
//        assertThat(errorResponse.getStatus()).isEqualTo(404);
//        assertThat(errorResponse.getMessage()).isEqualTo("Not Found");
//        assertThat(errorResponse.getTimestamp()).isEqualTo(fixedTime);
//        assertThat(errorResponse.getPath()).isEqualTo("/test/path");
//    }
//
//    @Test
//    void settersAndGettersShouldWorkCorrectly() {
//        LocalDateTime fixedTime = LocalDateTime.of(2025, 8, 9, 11, 45, 0);
//        ErrorResponse errorResponse = new ErrorResponse(0, null, null, null);
//
//        errorResponse.setStatus(500);
//        errorResponse.setMessage("Internal Server Error");
//        errorResponse.setTimestamp(fixedTime);
//        errorResponse.setPath("/error");
//
//        assertThat(errorResponse.getStatus()).isEqualTo(500);
//        assertThat(errorResponse.getMessage()).isEqualTo("Internal Server Error");
//        assertThat(errorResponse.getTimestamp()).isEqualTo(fixedTime);
//        assertThat(errorResponse.getPath()).isEqualTo("/error");
//    }
//
//    @Test
//    void equalsAndHashCodeShouldWork() {
//        LocalDateTime fixedTime = LocalDateTime.of(2025, 8, 9, 12, 0, 0);
//
//        ErrorResponse e1 = new ErrorResponse(400, "Bad Request", fixedTime, "/bad");
//        ErrorResponse e2 = new ErrorResponse(400, "Bad Request", fixedTime, "/bad");
//
//        assertThat(e1).isEqualTo(e2);
//        assertThat(e1.hashCode()).isEqualTo(e2.hashCode());
//    }
//
//    @Test
//    void toStringShouldContainFieldValues() {
//        LocalDateTime fixedTime = LocalDateTime.of(2025, 8, 9, 12, 30, 0);
//        ErrorResponse errorResponse = new ErrorResponse(401, "Unauthorized", fixedTime, "/secure");
//
//        String toString = errorResponse.toString();
//
//        assertThat(toString).contains("401");
//        assertThat(toString).contains("Unauthorized");
//        assertThat(toString).contains("/secure");
//    }
//}

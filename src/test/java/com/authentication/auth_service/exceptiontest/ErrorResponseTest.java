package com.authentication.auth_service.exceptiontest;

import com.authentication.auth_service.exception.ErrorResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseTest {

    @Test
    void allArgsConstructorShouldSetAllFields() {
        LocalDateTime now = LocalDateTime.now();
        ErrorResponse errorResponse = new ErrorResponse(404, "Not Found", now, "/test/path");

        assertThat(errorResponse.getStatus()).isEqualTo(404);
        assertThat(errorResponse.getMessage()).isEqualTo("Not Found");
        assertThat(errorResponse.getTimestamp()).isEqualTo(now);
        assertThat(errorResponse.getPath()).isEqualTo("/test/path");
    }

    @Test
    void settersAndGettersShouldWorkCorrectly() {
        LocalDateTime now = LocalDateTime.now();
        ErrorResponse errorResponse = new ErrorResponse(0, null, null, null);

        errorResponse.setStatus(500);
        errorResponse.setMessage("Internal Server Error");
        errorResponse.setTimestamp(now);
        errorResponse.setPath("/error");

        assertThat(errorResponse.getStatus()).isEqualTo(500);
        assertThat(errorResponse.getMessage()).isEqualTo("Internal Server Error");
        assertThat(errorResponse.getTimestamp()).isEqualTo(now);
        assertThat(errorResponse.getPath()).isEqualTo("/error");
    }
}

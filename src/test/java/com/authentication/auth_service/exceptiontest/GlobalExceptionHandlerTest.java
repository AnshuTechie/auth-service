package com.authentication.auth_service.exceptiontest;

import com.authentication.auth_service.exception.ErrorResponse;
import com.authentication.auth_service.exception.GlobalExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;
    private HttpServletRequest mockRequest;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        mockRequest = mock(HttpServletRequest.class);
    }

    @Test
    void handleGenericException_ShouldReturnBadRequest() {
        // Arrange
        when(mockRequest.getRequestURI()).thenReturn("/test/path");
        Exception ex = new Exception("Something went wrong");

        // Act
        ResponseEntity<ErrorResponse> response =
                globalExceptionHandler.handleException(ex, mockRequest);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().getMessage()).isEqualTo("Something went wrong");
        assertThat(response.getBody().getPath()).isEqualTo("/test/path");
    }

    @Test
    void handleValidationException_ShouldReturnBadRequestWithFieldErrorMessage() {
        // Arrange
        when(mockRequest.getRequestURI()).thenReturn("/test/validation");

        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("objectName", "fieldName", "Invalid value");
        when(bindingResult.getFieldError()).thenReturn(fieldError);

        MethodArgumentNotValidException validationException =
                new MethodArgumentNotValidException(null, bindingResult);

        // Act
        ResponseEntity<ErrorResponse> response =
                globalExceptionHandler.handleValidationException(validationException, mockRequest);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().getMessage()).isEqualTo("Invalid value");
        assertThat(response.getBody().getPath()).isEqualTo("/test/validation");
    }
}

package com.authentication.auth_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String message;
    private final LocalDateTime timestamp;
    private final String path;
}

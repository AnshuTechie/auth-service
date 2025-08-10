package com.authentication.auth_service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String path;
}






//package com.authentication.auth_service.exception;
//
//import lombok.Builder;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
///**
// * Represents a structured error response returned by the API
// * for consistent error handling on the client side.
// */
//@Getter
//@Builder
//public class ErrorResponse {
//    private final int status;
//    private final String message;
//    private final LocalDateTime timestamp;
//    private final String path;
//
//    public static ErrorResponse of(int status, String message, String path) {
//        return ErrorResponse.builder()
//                .status(status)
//                .message(message)
//                .timestamp(LocalDateTime.now())
//                .path(path)
//                .build();
//    }
//}

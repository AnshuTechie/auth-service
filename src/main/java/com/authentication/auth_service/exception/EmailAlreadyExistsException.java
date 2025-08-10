package com.authentication.auth_service.exception;

/**
 * Custom exception thrown when attempting to register with
 * an email address that already exists in the system.
 */
public class EmailAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L; // ✅ For consistent serialization

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}

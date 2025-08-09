package com.authentication.auth_service.exceptiontest;


import com.authentication.auth_service.exception.EmailAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailAlreadyExistsExceptionTest {

    @Test
    void constructorShouldSetMessage() {
        String message = "Email already exists!";
        EmailAlreadyExistsException exception = new EmailAlreadyExistsException(message);

        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(message);
    }
}

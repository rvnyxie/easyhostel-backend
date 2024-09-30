package com.easyhostel.backend.domain.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedAccessException extends ApplicationException {

    public UnauthorizedAccessException(String message) {
        super(message, "UNAUTHORIZED_ACCESS", HttpStatus.FORBIDDEN);
    }

}

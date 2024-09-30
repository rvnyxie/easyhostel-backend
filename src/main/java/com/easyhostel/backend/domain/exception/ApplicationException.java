package com.easyhostel.backend.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public abstract class ApplicationException extends RuntimeException {

    private final String errorCode;

    private final HttpStatus httpStatus;

    public ApplicationException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

}

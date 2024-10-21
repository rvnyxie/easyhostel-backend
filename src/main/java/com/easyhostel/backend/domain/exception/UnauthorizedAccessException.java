package com.easyhostel.backend.domain.exception;

import com.easyhostel.backend.domain.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class UnauthorizedAccessException extends ApplicationException {

    public UnauthorizedAccessException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }

}

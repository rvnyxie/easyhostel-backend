package com.easyhostel.backend.domain.exception;

import com.easyhostel.backend.domain.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class UnauthorizedAccessException extends ApplicationException {

    public UnauthorizedAccessException(String message) {
        super(String.format(ErrorCode.FORBIDDEN_ACCESS.getMessage()),
                ErrorCode.FORBIDDEN_ACCESS,
                HttpStatus.FORBIDDEN);
    }

}

package com.easyhostel.backend.domain.exception;

import com.easyhostel.backend.domain.enums.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * Custom exception for not found entity in DB
 *
 * @author Nyx
 */
public class EntityNotFoundException extends ApplicationException {

    public EntityNotFoundException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }

}

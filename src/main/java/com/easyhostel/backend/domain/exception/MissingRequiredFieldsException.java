package com.easyhostel.backend.domain.exception;

import com.easyhostel.backend.domain.enums.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * Custom exception for missing required field
 *
 * @author Nyx
 */
public class MissingRequiredFieldsException extends ApplicationException{

    public MissingRequiredFieldsException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }

}

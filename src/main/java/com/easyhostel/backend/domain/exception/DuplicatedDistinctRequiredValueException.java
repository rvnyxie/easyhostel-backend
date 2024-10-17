package com.easyhostel.backend.domain.exception;

import com.easyhostel.backend.domain.enums.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * Custom exception for duplicating distinct required field
 *
 * @author Nyx
 */
public class DuplicatedDistinctRequiredValueException extends ApplicationException{

    public DuplicatedDistinctRequiredValueException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }

}

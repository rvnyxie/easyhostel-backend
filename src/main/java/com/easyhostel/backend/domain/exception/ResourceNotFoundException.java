package com.easyhostel.backend.domain.exception;

import com.easyhostel.backend.domain.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String resource, String id) {
        super(String.format(ErrorCode.RESOURCE_NOT_FOUND.getMessage(), id),
                ErrorCode.RESOURCE_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

}

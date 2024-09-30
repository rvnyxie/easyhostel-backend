package com.easyhostel.backend.domain.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String resource, String id) {
        super(String.format("%s with ID %s not found", resource, id), "RESOURCE_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

}

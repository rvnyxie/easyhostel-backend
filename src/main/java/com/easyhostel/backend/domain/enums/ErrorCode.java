package com.easyhostel.backend.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "Resource with ID {0} not found"),

    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND", "Entity with ID {0} not found"),

    VALIDATION_ERROR("VALIDATION_ERROR", "Validation failed for {0}"),

    FORBIDDEN_ACCESS("FORBIDDEN_ACCESS", "You are not forbidden to access this resource"),

    UNAUTHORIZED_ACCESS("UNAUTHORIZED_ACCESS", "Unauthorized access"),

    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "An unexpected error occurred"),

    BUSINESS_LOGIC_ERROR("BUSINESS_LOGIC_ERROR", "Business logic failure: {0}"),

    DUPLICATED_VALUE("DUPLICATED_VALUE", "Value need to be distinct but duplicated");

    private final String code;

    private final String message;

}

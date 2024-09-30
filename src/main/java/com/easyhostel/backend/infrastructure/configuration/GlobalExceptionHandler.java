package com.easyhostel.backend.infrastructure.configuration;

import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.infrastructure.util.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobalException(final Exception ex) {
        log.error(ex.getMessage(), ex);
        var errorResponse = new ApiResponse<>(
                false,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "UNEXPECTED_EXCEPTION",
                "An unexpected error occurred",
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(final Exception ex) {
        log.error(ex.getMessage(), ex);
        var errorResponse = new ApiResponse<>(
                false,
                HttpStatus.NOT_FOUND.value(),
                "RESOURCE_NOT_FOUND",
                "Resource not found",
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ApiResponse<Object>> handleUnauthorizedAccessException(final Exception ex) {
        log.error(ex.getMessage(), ex);
        var errorResponse = new ApiResponse<>(
                false,
                HttpStatus.UNAUTHORIZED.value(),
                "UNAUTHORIZED_ACCESS",
                "Unauthorized access",
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(final MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        var errorResponse = new ApiResponse<>(
                false,
                HttpStatus.BAD_REQUEST.value(),
                "FAILED_VALIDATION",
                "Validation failed",
                errors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(AccessDeniedException ex) {
        var errorResponse = new ApiResponse<>(
                false,
                HttpStatus.FORBIDDEN.value(),
                "ACCESS_DENIED",
                "Access denied",
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Object>> handledAuthenticationException(AuthenticationException ex) {
        var errorResponse = new ApiResponse<>(
                false,
                HttpStatus.UNAUTHORIZED.value(),
                "UNAUTHORIZED",
                "Unauthorized access",
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

}

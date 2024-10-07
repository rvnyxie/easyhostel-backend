package com.easyhostel.backend.infrastructure.configuration;

import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.infrastructure.util.response.FormattedResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler which catches most important exceptions and returns formatted response
 *
 * @author Nyx
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Method that handles global Exception. The highest level Exception will be caught
     *
     * @param ex Exception being caught
     * @return Formatted response entity
     * @author Nyx
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<FormattedResponse<Object>> handleGlobalException(final Exception ex) {
        log.error(ex.getMessage(), ex);
        var errorResponse = new FormattedResponse<>(
                false,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method that handles resource not found exception
     *
     * @param ex No resource found exception
     * @return Formatted response entity
     * @author Nyx
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<FormattedResponse<Object>> handleResourceNotFoundException(final NoResourceFoundException ex) {
        log.error(ex.getMessage(), ex);
        var errorResponse = new FormattedResponse<>(
                false,
                HttpStatus.NOT_FOUND.value(),
                ErrorCode.RESOURCE_NOT_FOUND.getCode(),
                ErrorCode.RESOURCE_NOT_FOUND.getMessage(),
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Method that handles unauthorized access exception
     *
     * @param ex Unauthorized access exception
     * @return Formatted response entity
     * @author Nyx
     */
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<FormattedResponse<Object>> handleUnauthorizedAccessException(final Exception ex) {
        log.error(ex.getMessage(), ex);
        var errorResponse = new FormattedResponse<>(
                false,
                HttpStatus.UNAUTHORIZED.value(),
                ErrorCode.FORBIDDEN_ACCESS.getCode(),
                ErrorCode.FORBIDDEN_ACCESS.getMessage(),
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles invalidation in method argument (e.g.in @RequestBody)
     *
     * @param ex Invalid method argument exception
     * @return Formatted response entity
     * @author Nyx
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FormattedResponse<Map<String, String>>> handleValidationException(final MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        var errorResponse = new FormattedResponse<>(
                false,
                HttpStatus.BAD_REQUEST.value(),
                ErrorCode.VALIDATION_ERROR.getCode(),
                ErrorCode.VALIDATION_ERROR.getMessage(),
                errors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles constraint violation exception (e.g.in @RequestParam and @PathVariable)
     *
     * @param ex Constraint violation exception
     * @return Formatted response entity
     * @author Nyx
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<FormattedResponse<Map<String, String>>> handleConstraintViolationException(final ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);

        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(constraintViolation -> {
            String fieldName = constraintViolation.getPropertyPath().toString();
            String errorMessage = constraintViolation.getMessage();
            errors.put(fieldName, errorMessage);
        });

        var errorResponse = new FormattedResponse<>(
                false,
                HttpStatus.BAD_REQUEST.value(),
                ErrorCode.VALIDATION_ERROR.getCode(),
                ErrorCode.VALIDATION_ERROR.getMessage(),
                errors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles denied access exception
     *
     * @param ex Access denied exception
     * @return Formatted response entity
     * @author Nyx
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<FormattedResponse<Object>> handleAccessDeniedException(AccessDeniedException ex) {
        log.error(ex.getMessage(), ex);

        var errorResponse = new FormattedResponse<>(
                false,
                HttpStatus.FORBIDDEN.value(),
                ErrorCode.FORBIDDEN_ACCESS.getCode(),
                ErrorCode.FORBIDDEN_ACCESS.getMessage(),
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Handles authentication exception
     *
     * @param ex Authentication exception
     * @return Formatted response entity
     * @author Nyx
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<FormattedResponse<Object>> handledAuthenticationException(AuthenticationException ex) {
        log.error(ex.getMessage(), ex);

        var errorResponse = new FormattedResponse<>(
                false,
                HttpStatus.UNAUTHORIZED.value(),
                ErrorCode.UNAUTHORIZED_ACCESS.getCode(),
                ErrorCode.UNAUTHORIZED_ACCESS.getMessage(),
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle HttpMessageNotReadableException (e.g.failed to deserialize boolean field type)
     *
     * @param ex HttpMessageNotReadableException
     * @return Formatted response entity
     * @author Nyx
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<FormattedResponse<Map<String, String>>> handleHttpMessageNotReadableException(final HttpMessageNotReadableException ex) {
        log.error(ex.getCause().getMessage(), ex);

        Map<String, String> errors = new HashMap<>();

        var cause = ex.getCause();
        if (cause instanceof JsonMappingException jsonMappingException) {
            // Extract the field name that caused the error
            String fieldName = jsonMappingException.getPath().stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .findFirst()
                    .orElse("Unknown field");

            // Add the error message related to boolean parsing
            errors.put(fieldName, Translator.toLocale("validation.onlyBooleanAllowed"));
        } else {
            // Fallback error if it's a different type of parsing error
            errors.put("error", Translator.toLocale("validation.invalidRequestFormat"));
        }

        var errorResponse = new FormattedResponse<>(
                false,
                HttpStatus.BAD_REQUEST.value(),
                ErrorCode.VALIDATION_ERROR.getCode(),
                ErrorCode.VALIDATION_ERROR.getMessage(),
                errors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle custom EntityNotFoundException (e.g.can not find entity with ID)
     *
     * @param ex EntityNotFoundException
     * @return Formatted response entity
     * @author Nyx
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<FormattedResponse<Object>> handleResourceNotFoundException(final EntityNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        var errorResponse = new FormattedResponse<>(
                false,
                HttpStatus.NOT_FOUND.value(),
                ErrorCode.ENTITY_NOT_FOUND.getCode(),
                !ex.getMessage().isEmpty() ? ex.getMessage() : ErrorCode.ENTITY_NOT_FOUND.getMessage(),
                null
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}

package com.synchrony.exceptions;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.synchrony.utils.errorHandlers.MyErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler for all exceptions related to the application.
 * Handles specific exceptions like ResourceNotFoundException, BadCredentialsException, and validation errors.
 * <p>
 * This class ensures consistent error responses for different types of errors.
 * </p>
 *
 * @author Tejas
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle ResourceNotFoundException.
     *
     * @param se Exception instance
     * @param req WebRequest containing request details
     * @return ResponseEntity with error details and HTTP status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MyErrorDetails> resourceNotFoundExceptionHandler(Exception se, WebRequest req) {
        MyErrorDetails err = new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(se.getMessage());
        err.setDescription(req.getDescription(false));
        err.setErrorCode("RESOURCE_NOT_FOUND");
        err.setUrl(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle BadCredentialsException.
     *
     * @param se Exception instance
     * @param req WebRequest containing request details
     * @return ResponseEntity with error details and HTTP status
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MyErrorDetails> badCredentialsExceptionHandler(Exception se, WebRequest req) {
        MyErrorDetails err = new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(se.getMessage());
        err.setDescription(req.getDescription(false));
        err.setErrorCode("BAD_CREDENTIALS");
        err.setUrl(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle MethodArgumentNotValidException for validation errors.
     *
     * @param e Validation exception instance
     * @param req WebRequest containing request details
     * @return ResponseEntity with validation error details and HTTP status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e,
                                                                                      WebRequest req) {
        Map<String, String> validationErrors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            validationErrors.put(fieldName, message);
        });

        // Return validation errors with specific HTTP status
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle generic exceptions (e.g., IOException, etc.).
     *
     * @param se Exception instance
     * @param req WebRequest containing request details
     * @return ResponseEntity with error details and HTTP status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> exceptionHandler(Exception se, WebRequest req) {
        MyErrorDetails err = new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(se.getMessage());
        err.setDescription(req.getDescription(false));
        err.setErrorCode("GENERAL_ERROR");
        err.setUrl(req.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

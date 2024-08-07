package dev.warehouseService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the warehouse service application.
 * Provides methods to handle various exceptions and return appropriate HTTP responses.
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handles validation exceptions thrown when method arguments are not valid.
     *
     * @param ex the MethodArgumentNotValidException thrown when validation fails
     * @return a ResponseEntity containing a map of field errors and their corresponding messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

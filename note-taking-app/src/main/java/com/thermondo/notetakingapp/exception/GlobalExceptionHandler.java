package com.thermondo.notetakingapp.exception;

import com.thermondo.notetakingapp.model.BadRequestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, String> formatMessage(String message) {
        Map<String, String> result = new HashMap<>();
        result.put("message", message);

        return result;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        BadRequestResponse response = new BadRequestResponse(formatMessage(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(AccessDeniedException ex, WebRequest request) {
        BadRequestResponse response = new BadRequestResponse(formatMessage(ex.getMessage()));

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidUserSessionException.class)
    public ResponseEntity<?> invalidUserSessionException(InvalidUserSessionException ex, WebRequest request) {
        BadRequestResponse response = new BadRequestResponse(formatMessage(ex.getMessage()));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ex.printStackTrace();

        BadRequestResponse response = new BadRequestResponse(formatMessage(ex.getMessage()));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

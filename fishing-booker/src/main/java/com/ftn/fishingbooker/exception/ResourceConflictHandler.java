package com.ftn.fishingbooker.exception;

import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ResourceConflictHandler {

    @ExceptionHandler(value = {ResourceConflictException.class})
    public ResponseEntity<Object> handleResourceException(ResourceConflictException e) {
        //1. Create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ResourceException exception = new ResourceException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        //2. Return response entity
        return new ResponseEntity<>(exception, badRequest);
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<Object> handleIO(IOException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<Object> handleInvalidPassword(InvalidPasswordException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(exception.getMessage());
    }

    @ExceptionHandler(MessagingException.class)
    protected ResponseEntity<Object> handleMessagingException(MessagingException exception) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        return ResponseEntity.status(status).body(exception.getMessage());
    }

    @ExceptionHandler(StaleObjectStateException.class)
    protected ResponseEntity<Object> handleStaleObjectStateException(StaleObjectStateException exception) {
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(exception.getMessage());
    }
}

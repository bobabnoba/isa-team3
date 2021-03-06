package com.ftn.fishingbooker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}

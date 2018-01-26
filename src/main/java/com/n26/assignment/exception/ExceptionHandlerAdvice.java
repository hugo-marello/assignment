package com.n26.assignment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(AbstractResourceException.class)
    public ResponseEntity<String> handleException(AbstractResourceException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getUserMessage());
    }        
}
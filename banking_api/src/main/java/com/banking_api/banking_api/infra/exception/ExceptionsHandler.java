package com.banking_api.banking_api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

 @ExceptionHandler(EntityNotFoundException.class)
 public ResponseEntity<?> notFound404() {
        return ResponseEntity.notFound().build();
    }

@ExceptionHandler (InsufficientBalanceException.class)
    public ResponseEntity<?> forbidden403 (InsufficientBalanceException exception) {

     return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
}

}
package com.banking_api.banking_api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> notFound404(EntityNotFoundException exception) {
   var msg = exception.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }


    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> paymentRequired402(ApiException exception) {

        var httpStatus = exception.getStatus();

        return ResponseEntity.status(httpStatus).body(exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<?> UnaouthorizedUser401(ApiException exception) {

        var httpStatus = exception.getStatus();

        return ResponseEntity.status(httpStatus).body(exception.getMessage());
    }

    @ExceptionHandler(BadResponseException.class)
    public ResponseEntity<?> badResponseFromProvider200 (ApiException exception) {

        var httpStatus = exception.getStatus();

        return ResponseEntity.status(httpStatus).body(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> badFunction (RuntimeException exception) {

        var msg = exception.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
    }

}

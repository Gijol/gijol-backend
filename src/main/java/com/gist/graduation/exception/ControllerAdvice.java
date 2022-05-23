package com.gist.graduation.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationException(ApplicationException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorResponse(ex.getMessage()));
    }
}

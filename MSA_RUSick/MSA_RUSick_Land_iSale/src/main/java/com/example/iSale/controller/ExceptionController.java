package com.example.iSale.controller;

import com.example.iSale.exception.ISaleErrorResponse;
import com.example.iSale.exception.ISaleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ISaleException.class)
    public ResponseEntity<ISaleErrorResponse> handleISaleException (ISaleException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ISaleErrorResponse.builder()
                        .errorCode(e.getErrorCode())
                        .message(e.getMessage())
                        .build());
    }
}


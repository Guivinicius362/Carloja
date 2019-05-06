package com.teste.carloja.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDetails> handlerApiException(ApiException apiException) {
        ErrorDetails errorDetails = new ErrorDetails(apiException.getMensagem(), apiException.getStatusCode());
        return ResponseEntity.status(apiException.getStatusCode()).body(errorDetails);
    }
}

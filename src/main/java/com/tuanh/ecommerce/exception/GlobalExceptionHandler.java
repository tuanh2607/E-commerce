package com.tuanh.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tuanh.ecommerce.dto.response.Response;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Response<Void>> handlingAppException(AppException exception){
        Response<Void> response = Response.<Void>builder()
                                        .code(exception.getCode().getCode())
                                        .message(exception.getMessage())
                                        .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

package com.tuanh.ecommerce.enums;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    CREATE_USER(2001, "Create user successful", HttpStatus.CREATED),
    UNABLE_TOKEN(2002, "Unbale token successful", HttpStatus.ACCEPTED)
    ;
    private int code;
    private String message;
    private HttpStatusCode status;
}

package com.tuanh.ecommerce.enums;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Code {
    CREATE_USER(1001, "[!] Create user successful", HttpStatus.ACCEPTED),
    USER_EXISTED(1002, "[!] User existed", HttpStatus.BAD_REQUEST)
    ;
    private int code;
    private String message;
    private HttpStatusCode status;
}

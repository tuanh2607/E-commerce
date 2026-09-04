package com.tuanh.ecommerce.enums;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_EXISTED(1001, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1002, "User is not exist", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1003, "Invalid password", HttpStatus.BAD_REQUEST),
    ERROR_CREATE_TOKEN(1004, "Cannot create token", HttpStatus.BAD_REQUEST),
    USER_ROLE_NOT_EXIST(1005, "User role is not exist", HttpStatus.INTERNAL_SERVER_ERROR),
    ADMIN_ROLE_NOT_EXIST(1006, "Admin role is not exist", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.BAD_REQUEST),
    ERROR_TOKEN(1007, "Token is not valid", HttpStatus.BAD_REQUEST)
    ;
    private int code;
    private String message;
    private HttpStatusCode status;
}

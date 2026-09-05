package com.tuanh.ecommerce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_EXISTED(1001, "User existed"),
    USER_NOT_EXIST(1002, "User is not exist"),
    INVALID_PASSWORD(1003, "Invalid password"),
    ERROR_CREATE_TOKEN(1004, "Cannot create token"),
    USER_ROLE_NOT_EXIST(1005, "User role is not exist"),
    ADMIN_ROLE_NOT_EXIST(1006, "Admin role is not exist"),
    UNAUTHENTICATED(1006, "Unauthenticated"),
    ERROR_TOKEN(1007, "Token is not valid"),
    CATEGORY_NOT_EXIST(1008, "Category is not exist")
    ;
    private int code;
    private String message;
}

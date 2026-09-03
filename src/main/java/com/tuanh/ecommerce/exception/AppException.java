package com.tuanh.ecommerce.exception;

import com.tuanh.ecommerce.enums.ErrorCode;

import lombok.Getter;


@Getter
public class AppException extends RuntimeException{
    public AppException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }
    private ErrorCode code;
}

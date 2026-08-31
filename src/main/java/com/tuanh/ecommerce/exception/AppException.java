package com.tuanh.ecommerce.exception;

import com.tuanh.ecommerce.enums.Code;

import lombok.Getter;


@Getter
public class AppException extends RuntimeException{
    public AppException(Code code){
        super(code.getMessage());
        this.code = code;
    }
    private Code code;
}

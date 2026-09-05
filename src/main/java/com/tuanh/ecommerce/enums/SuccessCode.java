package com.tuanh.ecommerce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    CREATE_USER(2001, "Create user successful"),
    UNABLE_TOKEN(2002, "Unbale token successful"),
    CREATE_CATEGORY(2003, "Create category successful")
    ;
    private int code;
    private String message;
}

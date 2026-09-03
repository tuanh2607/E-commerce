package com.tuanh.ecommerce.enums.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {
    USER("User role"),
    ADMIN("Admin role")
    ;
    private String descripton;
}

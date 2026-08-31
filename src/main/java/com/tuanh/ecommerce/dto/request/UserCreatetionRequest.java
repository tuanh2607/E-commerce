package com.tuanh.ecommerce.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreatetionRequest {
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String phone;
}

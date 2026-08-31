package com.tuanh.ecommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuanh.ecommerce.dto.request.UserCreatetionRequest;
import com.tuanh.ecommerce.dto.response.Response;
import com.tuanh.ecommerce.dto.response.UserCreationResponse;
import com.tuanh.ecommerce.enums.Code;
import com.tuanh.ecommerce.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController // = @ResponseBody + @Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
      
    @PostMapping("/register")
    public Response<UserCreationResponse> createUser(@RequestBody UserCreatetionRequest request){
        return Response.<UserCreationResponse>builder()
                    .code(Code.CREATE_USER.getCode())
                    .message(Code.CREATE_USER.getMessage())
                    .result(userService.createUser(request))
                    .build();
    }
}

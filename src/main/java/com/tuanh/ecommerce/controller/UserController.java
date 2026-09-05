package com.tuanh.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuanh.ecommerce.dto.request.UserCreatetionRequest;
import com.tuanh.ecommerce.dto.response.GetInfoUserResponse;
import com.tuanh.ecommerce.dto.response.Response;
import com.tuanh.ecommerce.dto.response.UserCreationResponse;
import com.tuanh.ecommerce.enums.SuccessCode;
import com.tuanh.ecommerce.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController // = @ResponseBody + @Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
      
    @PostMapping("/register")
    public ResponseEntity<Response<UserCreationResponse>> createUser(@RequestBody UserCreatetionRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.<UserCreationResponse>builder()
                    .code(SuccessCode.CREATE_USER.getCode())
                    .message(SuccessCode.CREATE_USER.getMessage())
                    .result(userService.createUser(request))
                    .build());
    }

    @GetMapping("/me")
    public ResponseEntity<Response<GetInfoUserResponse>> getMyInfo(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Response.<GetInfoUserResponse>builder()
                        .result(userService.getMyInfo())
                        .build());
    }
}

package com.tuanh.ecommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuanh.ecommerce.dto.request.AuthenticationRequest;
import com.tuanh.ecommerce.dto.request.LogoutRequest;
import com.tuanh.ecommerce.dto.response.AuthenticationResponse;
import com.tuanh.ecommerce.dto.response.Response;
import com.tuanh.ecommerce.enums.SuccessCode;
import com.tuanh.ecommerce.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    
    @PostMapping("/login")
    public Response<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return Response.<AuthenticationResponse>builder()
                            .result(authenticationService.authenticate(request))
                            .build();
    }

    @PostMapping("/logout")
    public Response<Void> logout(@RequestBody LogoutRequest request){
        authenticationService.logout(request);
        return Response.<Void>builder()
                    .message(SuccessCode.UNABLE_TOKEN.getMessage())
                    .code(SuccessCode.UNABLE_TOKEN.getCode())
                    .build();
    }
}

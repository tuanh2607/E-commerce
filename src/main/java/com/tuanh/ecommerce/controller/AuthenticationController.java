package com.tuanh.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response<AuthenticationResponse>> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Response.<AuthenticationResponse>builder()
                            .result(authenticationService.authenticate(request))
                            .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<Response<Void>> logout(@RequestBody LogoutRequest request){
        authenticationService.logout(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Response.<Void>builder()
                    .message(SuccessCode.UNABLE_TOKEN.getMessage())
                    .code(SuccessCode.UNABLE_TOKEN.getCode())
                    .build());
    }
    
}

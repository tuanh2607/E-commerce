package com.tuanh.ecommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuanh.ecommerce.dto.request.AuthenticationRequest;
import com.tuanh.ecommerce.dto.response.AuthenticationResponse;
import com.tuanh.ecommerce.dto.response.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    
    @PostMapping("/login")
    public Response<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return Response.<AuthenticationResponse>builder()
                            .build();
    }
}

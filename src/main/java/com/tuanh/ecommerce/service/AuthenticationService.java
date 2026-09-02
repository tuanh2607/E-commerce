package com.tuanh.ecommerce.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuanh.ecommerce.dto.request.AuthenticationRequest;
import com.tuanh.ecommerce.dto.response.AuthenticationResponse;
import com.tuanh.ecommerce.entity.user.User;
import com.tuanh.ecommerce.enums.Code;
import com.tuanh.ecommerce.exception.AppException;
import com.tuanh.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthenticationResponse authenticate(AuthenticationRequest request){
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(Code.USER_NOT_EXIST));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticated) throw new AppException(Code.INVALID_PASSWORD);
        return AuthenticationResponse.builder()
                 .token(generateToken(user))
                 .build();
    }
    public String generateToken(User user){
         return "HAHAHA";
    }
}

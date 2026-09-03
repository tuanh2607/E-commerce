package com.tuanh.ecommerce.security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder{
    private final JwtDecoder nimbusJwtDecoder;

    @Override
    public Jwt decode(String token) throws JwtException{
        return nimbusJwtDecoder.decode(token);
    }
}

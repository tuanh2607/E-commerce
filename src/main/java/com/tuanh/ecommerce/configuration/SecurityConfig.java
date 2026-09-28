package com.tuanh.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.tuanh.ecommerce.security.CustomJwtAuthenticationEntryPoint;
import com.tuanh.ecommerce.security.CustomJwtDecoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final String[] PUBLIC_POST_ENDPOINT = {"/users/**", "/auth/**", "/products/**"};
    private final CustomJwtDecoder customJwtDecoder;
    private final JwtAuthenticationConverter jwtAuthenticationConverter;
    private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.authorizeHttpRequests(request -> 
            request.requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINT).permitAll()
                   .anyRequest().authenticated()
        );
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfig -> jwtConfig.decoder(customJwtDecoder)
                                                                             .jwtAuthenticationConverter(jwtAuthenticationConverter))
                                                  .authenticationEntryPoint(customJwtAuthenticationEntryPoint)   
                                );
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ 
        return new BCryptPasswordEncoder(12);
    }
}

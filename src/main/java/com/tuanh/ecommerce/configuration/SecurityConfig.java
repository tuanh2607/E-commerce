package com.tuanh.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PUBLIC_POST_ENDPOINT = {"/users", "/auth/login"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.authorizeHttpRequests(request -> 
            request.requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINT).permitAll()
                   .anyRequest().authenticated()
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ // Encode user when register
        return new BCryptPasswordEncoder(12);
    }
}

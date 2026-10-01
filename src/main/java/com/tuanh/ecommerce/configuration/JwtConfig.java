package com.tuanh.ecommerce.configuration;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class JwtConfig {
    @Value("${jwt.signerKey}")
    private String signerKey;

    @Bean
    public JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
        // Set clock skew về 0 giây
        // jwtDecoder.setJwtValidator(new JwtTimestampValidator(Duration.ofSeconds(0)));
        return jwtDecoder;
    }

    @Bean 
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGranted = new JwtGrantedAuthoritiesConverter();
        jwtGranted.setAuthoritiesClaimName("roles");
        jwtGranted.setAuthorityPrefix("");
        
        JwtAuthenticationConverter jwtAuthentication = new JwtAuthenticationConverter();
        jwtAuthentication.setJwtGrantedAuthoritiesConverter(jwtGranted);
        return jwtAuthentication;
    }
}

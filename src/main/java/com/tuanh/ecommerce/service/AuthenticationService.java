package com.tuanh.ecommerce.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tuanh.ecommerce.dto.request.AuthenticationRequest;
import com.tuanh.ecommerce.dto.request.LogoutRequest;
import com.tuanh.ecommerce.dto.response.AuthenticationResponse;
import com.tuanh.ecommerce.entity.user.InvalidatedToken;
import com.tuanh.ecommerce.entity.user.User;
import com.tuanh.ecommerce.enums.ErrorCode;
import com.tuanh.ecommerce.exception.AppException;
import com.tuanh.ecommerce.repository.InvalidatedTokenRepository;
import com.tuanh.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; 
    private final InvalidatedTokenRepository invalidatedTokenRepository;

    @Value("${jwt.signerKey}")
    private String signerKey;

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticated) throw new AppException(ErrorCode.INVALID_PASSWORD);
        return AuthenticationResponse.builder()
                .fullname(user.getFullname())
                .token(generateToken(user))
                .build();
    }
    public String generateToken(User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                                                .subject(user.getUsername())
                                                .issuer("admin")
                                                .issueTime(new Date())
                                                .expirationTime(new Date(Instant.now().plus(3600, ChronoUnit.SECONDS).toEpochMilli()))
                                                .jwtID(UUID.randomUUID().toString())
                                                .claim("roles", buildRoles(user))
                                                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException exception){
            log.info("Cannot create token");
            throw new AppException(ErrorCode.ERROR_CREATE_TOKEN);
        }
    }

    public String buildRoles(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles())){
            user.getRoles().forEach(role -> {
                stringJoiner.add(role.getName());
            });
        }
        return stringJoiner.toString();
    }

    public SignedJWT verifyToken(String token) throws JOSEException, ParseException{
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

        boolean authenticated = signedJWT.verify(verifier);
        boolean expiry = claimsSet.getExpirationTime().after(new Date());
        boolean invalidToken = invalidatedTokenRepository.existsById(claimsSet.getJWTID());

        if(!authenticated || !expiry || invalidToken) throw new AppException(ErrorCode.UNAUTHENTICATED);
        return signedJWT;
    }

    public void logout(LogoutRequest request){
        String userToken = request.getToken();
        try {
            SignedJWT signedJWT = verifyToken(userToken);
            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

            InvalidatedToken token = InvalidatedToken.builder()
                                            .id(claimsSet.getJWTID())
                                            .expiryTime(claimsSet.getExpirationTime())
                                            .build();

            invalidatedTokenRepository.save(token);
        } catch (JOSEException | ParseException exception){
            throw new AppException(ErrorCode.ERROR_TOKEN);
        }
    }
}

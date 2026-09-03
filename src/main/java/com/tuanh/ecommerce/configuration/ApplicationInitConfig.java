package com.tuanh.ecommerce.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tuanh.ecommerce.entity.user.Role;
import com.tuanh.ecommerce.entity.user.User;
import com.tuanh.ecommerce.enums.ErrorCode;
import com.tuanh.ecommerce.enums.auth.Authority;
import com.tuanh.ecommerce.exception.AppException;
import com.tuanh.ecommerce.repository.RoleRepository;
import com.tuanh.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationInitConfig {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${account.admin.email}")
    private String admin_email;

    @Value("${account.admin.username}")
    private String admin_username;

    @Value("${account.admin.password}")
    private String admin_password;

    @Value("${account.admin.fullname}")
    private String admin_fullname;

    @Value("${account.admin.phone}")
    private String admin_phone;


    @Bean
    public ApplicationRunner applicationRunner(){
        return args -> {
            if(!roleRepository.existsByName(Authority.USER.toString())){
                Role role = Role.builder()
                                .name(Authority.USER.toString())
                                .description(Authority.USER.getDescripton())
                                .build();
                roleRepository.save(role);
                log.info("[!] Role user has been created");
            }

            if(!roleRepository.existsByName(Authority.ADMIN.toString())){
                Role role = Role.builder()
                                .name(Authority.ADMIN.toString())
                                .description(Authority.ADMIN.getDescripton())
                                .build();
                roleRepository.save(role);
                log.info("[!] Role admin has been created");
            }

            if(!userRepository.existsByUsername(Authority.ADMIN.toString())){
                Set<Role> roles = new HashSet<>();
                Role userRole = roleRepository.findByName(Authority.USER.toString()).orElseThrow(() -> new AppException(ErrorCode.USER_ROLE_NOT_EXIST));
                Role adminRole = roleRepository.findByName(Authority.ADMIN.toString()).orElseThrow(() -> new AppException(ErrorCode.ADMIN_ROLE_NOT_EXIST));
                roles.add(userRole);
                roles.add(adminRole);

                User admin = User.builder()
                                .fullname(admin_fullname)
                                .username(admin_password)
                                .password(passwordEncoder.encode(admin_password))
                                .email(admin_email)
                                .phone(admin_phone)
                                .roles(roles)
                                .build();
                userRepository.save(admin);
                log.info("[!] Admin account has been created with default password : {}", admin_password);
            }
        };
    }
}

package com.tuanh.ecommerce.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.tuanh.ecommerce.entity.user.Role;
import com.tuanh.ecommerce.entity.user.User;
import com.tuanh.ecommerce.enums.Authority;
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

    private final String admin_email = "admin@gmail.com";
    private final String admin_phone = "admin";
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
                Role userRole = roleRepository.findByName(Authority.USER.toString()).orElseThrow(() -> new RuntimeException("Can not create Admin account because user role is not exist"));
                Role adminRole = roleRepository.findByName(Authority.ADMIN.toString()).orElseThrow(() -> new RuntimeException("Can not create Admin account because admin role is not exist"));
                roles.add(userRole);
                roles.add(adminRole);

                User admin = User.builder()
                                .fullname("admin")
                                .username("admin")
                                .password("admin")
                                .email(admin_email)
                                .phone(admin_phone)
                                .roles(roles)
                                .build();
                userRepository.save(admin);
                log.info("[!] Admin account has been created with default password : admin");
            }
        };
    }
}

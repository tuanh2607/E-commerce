package com.tuanh.ecommerce.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuanh.ecommerce.entity.user.User;
import com.tuanh.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{     
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("[!] User name not found :" + username));
        return org.springframework.security.core.userdetails.User.builder()
                                                .username(user.getUsername())
                                                .password(user.getPassword())
                                                .authorities(
                                                    user.getRoles().stream()
                                                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                                                    .toList()
                                                )
                                                .build();
    }
}

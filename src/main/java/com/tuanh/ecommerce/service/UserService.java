package com.tuanh.ecommerce.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuanh.ecommerce.dto.request.UserCreatetionRequest;
import com.tuanh.ecommerce.dto.response.GetInfoUserResponse;
import com.tuanh.ecommerce.dto.response.UserResponse;
import com.tuanh.ecommerce.entity.cart_order.Cart;
import com.tuanh.ecommerce.entity.user.Role;
import com.tuanh.ecommerce.entity.user.User;
import com.tuanh.ecommerce.enums.ErrorCode;
import com.tuanh.ecommerce.enums.auth.Authority;
import com.tuanh.ecommerce.exception.AppException;
import com.tuanh.ecommerce.mapper.UserMapper;
import com.tuanh.ecommerce.repository.CartRepository;
import com.tuanh.ecommerce.repository.RoleRepository;
import com.tuanh.ecommerce.repository.UserRepository;
import com.tuanh.ecommerce.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;

    public UserResponse createUser(UserCreatetionRequest request){
        if(userRepository.existsByUsername(request.getUsername())){ // Suggest update : Indexing for username
            throw new AppException(ErrorCode.USER_EXISTED);
        } 
        User user = userMapper.fromCreationUserRequestToUser(request);
        Role role = roleRepository.findByName(Authority.USER.toString()).orElseThrow(() -> new AppException(ErrorCode.USER_ROLE_NOT_EXIST));
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        Cart cart = Cart.builder()
                        .user(savedUser)
                        .build();
        cartRepository.save(cart);
        return userMapper.fromUserToUserCreationResponse(savedUser);
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    public GetInfoUserResponse getMyInfo(){
        String username = SecurityUtils.getCurrentUsernameInSecurityContext();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        return userMapper.fromUserToGetInfoUserResponse(user);
    }
}

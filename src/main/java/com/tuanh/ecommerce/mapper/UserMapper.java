package com.tuanh.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tuanh.ecommerce.dto.request.UserCreatetionRequest;
import com.tuanh.ecommerce.dto.response.GetInfoUserResponse;
import com.tuanh.ecommerce.dto.response.UserResponse;
import com.tuanh.ecommerce.entity.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    public User fromCreationUserRequestToUser(UserCreatetionRequest request);

    public UserResponse fromUserToUserCreationResponse(User user);
    public GetInfoUserResponse fromUserToGetInfoUserResponse(User user);
}

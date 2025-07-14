package com.newspaper.identityservice.mapper;

import com.newspaper.identityservice.dto.request.UserCreationRequest;
import com.newspaper.identityservice.dto.request.UserUpdateRequest;
import com.newspaper.identityservice.dto.response.UserResponse;
import com.newspaper.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

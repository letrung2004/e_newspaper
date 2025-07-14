package com.newspaper.identityservice.service;

import com.newspaper.identityservice.dto.request.UserCreationRequest;
import com.newspaper.identityservice.dto.request.UserUpdateRequest;
import com.newspaper.identityservice.dto.response.UserResponse;
import com.newspaper.identityservice.entity.User;
import com.newspaper.identityservice.exception.AppException;
import com.newspaper.identityservice.exception.ErrorCode;
import com.newspaper.identityservice.mapper.UserMapper;
import com.newspaper.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).
                orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }


    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }
}


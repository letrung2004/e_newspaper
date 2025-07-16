package com.newspaper.identityservice.mapper;

import com.newspaper.identityservice.dto.request.PermissionRequest;
import com.newspaper.identityservice.dto.response.PermissionResponse;
import com.newspaper.identityservice.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);
    PermissionResponse toPermissionResponse(Permission permission);
}

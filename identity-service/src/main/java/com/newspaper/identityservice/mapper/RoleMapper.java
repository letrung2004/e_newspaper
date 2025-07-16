package com.newspaper.identityservice.mapper;

import com.newspaper.identityservice.dto.request.RoleRequest;
import com.newspaper.identityservice.dto.response.RoleResponse;
import com.newspaper.identityservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}

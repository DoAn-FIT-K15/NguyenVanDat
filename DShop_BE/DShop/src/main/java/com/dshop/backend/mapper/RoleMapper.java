package com.dshop.backend.mapper;


import com.dshop.backend.models.Role;
import com.dshop.backend.web.dto.response.RoleResponse;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    RoleResponse mapModelToResponse(Role role);
}

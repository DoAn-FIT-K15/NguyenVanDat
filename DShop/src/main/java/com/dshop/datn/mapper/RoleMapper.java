package com.dshop.datn.mapper;

import com.dshop.datn.models.Role;
import com.dshop.datn.web.dto.response.RoleResponse;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    RoleResponse mapModelToResponse(Role role);
}

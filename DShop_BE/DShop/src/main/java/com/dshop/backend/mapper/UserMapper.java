package com.dshop.backend.mapper;

import com.dshop.backend.models.User;
import com.dshop.backend.web.dto.request.AddEmpRequest;
import com.dshop.backend.web.dto.request.RegisterRequest;
import com.dshop.backend.web.dto.request.UserRequest;
import com.dshop.backend.web.dto.response.UserResponse;
import org.mapstruct.*;

@Mapper(uses = AddressMapper.class)
public interface UserMapper {
    /**
     *
     */
    User mapSignupToModel(RegisterRequest registerRequest);

    User mapSignupToModel2(AddEmpRequest addEmpRequest);

    /**
     *
     */
    @Mapping(target = "roles",source = "roles")
    @Mapping(target = "addresses", source = "addresses")
    UserResponse mapModelToResponse(User user);

    //User mapRequestToModel(UserRequest userRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(@MappingTarget User user, UserRequest userRequest);

}

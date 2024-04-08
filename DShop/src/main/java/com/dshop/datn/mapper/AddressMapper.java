package com.dshop.datn.mapper;

import com.dshop.datn.models.Address;
import com.dshop.datn.web.dto.request.AddressRequest;
import com.dshop.datn.web.dto.response.AddressResponse;
import org.mapstruct.*;

@Mapper
public interface AddressMapper {
    AddressResponse mapToResponse(Address address);

    @Mapping(target = "user.id", source = "userId")
    Address mapToModel(AddressRequest addressRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(@MappingTarget Address address, AddressRequest addressRequest);
}

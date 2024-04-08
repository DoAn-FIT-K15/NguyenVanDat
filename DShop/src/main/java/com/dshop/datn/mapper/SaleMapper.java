package com.dshop.datn.mapper;

import com.dshop.datn.models.Sale;
import com.dshop.datn.web.dto.request.SaleRequest;
import com.dshop.datn.web.dto.response.SaleResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = ProductMapper.class)
public interface SaleMapper {

    SaleResponse mapModelToResponse(Sale sale);
    Sale mapRequestToModel(SaleRequest saleRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(@MappingTarget Sale Sale, SaleRequest saleRequest);

}

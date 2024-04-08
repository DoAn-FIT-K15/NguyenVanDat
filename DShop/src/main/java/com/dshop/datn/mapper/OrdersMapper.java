package com.dshop.datn.mapper;

import com.dshop.datn.models.Orders;
import com.dshop.datn.web.dto.request.OrdersRequest;
import com.dshop.datn.web.dto.response.OrdersResponse;
import org.mapstruct.*;

@Mapper(uses = OrderItemMapper.class)
public interface OrdersMapper {
    @Mapping(target = "items", source = "orderItems")
    @Mapping(target = "user", source = "user.id")
    OrdersResponse mapToResponse(Orders orders);

    Orders mapToModel(OrdersRequest ordersRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(@MappingTarget Orders orders, OrdersRequest ordersRequest);
}

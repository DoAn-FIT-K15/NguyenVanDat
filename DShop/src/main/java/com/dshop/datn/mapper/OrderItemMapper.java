package com.dshop.datn.mapper;

import com.dshop.datn.models.OrderItem;
import com.dshop.datn.web.dto.request.OrderItemRequest;
import com.dshop.datn.web.dto.response.OrderItemResponse;
import org.mapstruct.Mapper;

@Mapper
public interface OrderItemMapper {
    OrderItemResponse mapToResponse(OrderItem orderItem);

    OrderItem mapToModel(OrderItemRequest orderItemRequest);
}

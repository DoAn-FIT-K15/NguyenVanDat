package com.dshop.datn.mapper;

import com.dshop.datn.models.Notification;
import com.dshop.datn.web.dto.response.NotificationResponse;
import org.mapstruct.*;

@Mapper
public interface NotificationMapper {
    @Mapping(target = "orders", source = "orders.id")
    @Mapping(target = "product", source = "product.id")
    NotificationResponse mapToResponse(Notification notification);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateModel(@MappingTarget Notification notification, NotificationResponse notificationResponse);
}

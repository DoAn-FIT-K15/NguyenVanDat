package com.dshop.datn.mapper;

import com.dshop.datn.web.dto.request.BannerRequest;
import com.dshop.datn.web.dto.response.BannerResponse;
import com.dshop.datn.models.Banner;
import org.mapstruct.*;

@Mapper
public interface BannerMapper {
	@Mapping(target = "categoryId", source = "category.id")
    BannerResponse mapModelToResponse(Banner banner);

	Banner mapRequestToModel(BannerRequest bannerRequest);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateModel(@MappingTarget Banner banner, BannerRequest bannerRequest);
}

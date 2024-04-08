package com.dshop.datn.mapper;

import com.dshop.datn.models.ProductImage;
import com.dshop.datn.web.dto.response.ProductImageResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ProductImageMapper {
	ProductImageResponse mapModelToResponse(ProductImage productImage);
}

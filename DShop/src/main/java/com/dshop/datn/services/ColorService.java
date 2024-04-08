package com.dshop.datn.services;

import com.dshop.datn.models.dtos.ColorDto;
import com.dshop.datn.web.dto.response.ColorResponse;

import java.util.List;

public interface ColorService {
    List<ColorDto> getAllValueColor();

    List<ColorResponse> getColorByProduct(Long productId);
}

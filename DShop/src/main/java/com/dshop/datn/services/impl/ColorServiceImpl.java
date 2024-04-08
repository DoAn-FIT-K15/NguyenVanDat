package com.dshop.datn.services.impl;

import com.dshop.datn.mapper.ColorMapper;
import com.dshop.datn.models.Color;
import com.dshop.datn.models.Product;
import com.dshop.datn.models.dtos.ColorDto;
import com.dshop.datn.repositories.ColorRepository;
import com.dshop.datn.repositories.ProductRepository;
import com.dshop.datn.services.ColorService;
import com.dshop.datn.web.dto.response.ColorResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    private final ProductRepository productRepository;
    private final ColorMapper colorMapper;

    public ColorServiceImpl(ColorRepository colorRepository,
                            ColorMapper colorMapper,
                            ProductRepository productRepository) {
        this.colorRepository = colorRepository;
        this.colorMapper = colorMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ColorDto> getAllValueColor() {
        List<String> distinctColorValues = colorRepository.findDistinctColorValues();
        return distinctColorValues.stream()
                .map(ColorDto::new)
                .collect(Collectors.toList());
    }
    @Override
    public List<ColorResponse> getColorByProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        List<Color> colors = colorRepository.findByProduct(product);
        return colors.stream()
                .map(colorMapper::mapModelToResponse)
                .toList();
    }
}

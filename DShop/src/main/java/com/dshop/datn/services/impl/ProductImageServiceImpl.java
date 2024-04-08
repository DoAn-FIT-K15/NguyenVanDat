package com.dshop.datn.services.impl;

import com.dshop.datn.mapper.ProductImageMapper;
import com.dshop.datn.models.ProductImage;
import com.dshop.datn.repositories.ProductImageRepository;
import com.dshop.datn.services.ProductImageService;
import org.springframework.stereotype.Service;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductImageMapper productImageMapper;

    public ProductImageServiceImpl(ProductImageRepository productImageRepository,
                                   ProductImageMapper productImageMapper) {
        this.productImageRepository = productImageRepository;
        this.productImageMapper = productImageMapper;
    }

    @Override
    public void delete(long id) {
        ProductImage productImage = productImageRepository.findById(id).orElseThrow();
        productImage.setProduct(null);
        productImageRepository.save(productImage);
        productImageRepository.delete(productImage);
    }
}

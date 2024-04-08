package com.dshop.datn.services.impl;

import com.dshop.datn.mapper.SizeMapper;
import com.dshop.datn.models.dtos.SizeDto;
import com.dshop.datn.repositories.SizeRepository;
import com.dshop.datn.services.SizeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    private final SizeMapper sizeMapper;

    public SizeServiceImpl(SizeRepository sizeRepository,
                           SizeMapper sizeMapper) {
        this.sizeRepository = sizeRepository;
        this.sizeMapper = sizeMapper;
    }

    @Override
    public List<SizeDto> getAllValueSize() {
        List<String> distinctSizeValues = sizeRepository.findDistinctSizeValues();
        return distinctSizeValues.stream()
                .map(SizeDto::new)
                .collect(Collectors.toList());
    }
}

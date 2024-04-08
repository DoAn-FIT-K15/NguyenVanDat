package com.dshop.datn.mapper;

import com.dshop.datn.models.SocialMedia;
import com.dshop.datn.models.dtos.SocialMediaDto;
import org.mapstruct.Mapper;

@Mapper
public interface SocialMediaMapper {
    SocialMediaDto mapToDto(SocialMedia socialMedia);
    SocialMedia mapToModel(SocialMediaDto socialMediaDto);

}

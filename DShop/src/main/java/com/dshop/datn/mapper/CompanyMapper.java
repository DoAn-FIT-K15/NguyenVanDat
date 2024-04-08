package com.dshop.datn.mapper;

import com.dshop.datn.models.Company;
import com.dshop.datn.web.dto.request.CompanyRequest;
import com.dshop.datn.web.dto.response.CompanyResponse;
import org.mapstruct.*;

@Mapper(uses = SocialMediaMapper.class)
public interface CompanyMapper {

    @Mapping(target = "socials", source = "socialMedias")
    CompanyResponse mapToResponse(Company company);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "socialMedias", source = "socialMedias")
    Company mapToModel(CompanyRequest companyRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModel(@MappingTarget Company company, CompanyRequest companyRequest);
}

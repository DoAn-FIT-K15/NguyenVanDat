package com.dshop.datn.services;

import com.dshop.datn.web.dto.request.CompanyRequest;
import com.dshop.datn.web.dto.response.CompanyResponse;

public interface CompanyService {
    CompanyResponse getCompany(long companyId);
    CompanyResponse createCompanyInfo(CompanyRequest companyRequest);
    CompanyResponse updateCompanyInfo(long companyId, CompanyRequest companyRequest);
}

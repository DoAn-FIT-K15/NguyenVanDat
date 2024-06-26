package com.dshop.datn.repositories;

import com.dshop.datn.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByIdAndStatus(long companyId, int status);
}

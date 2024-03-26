package com.dshop.backend.repositories;

import com.dshop.backend.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByIdAndStatus(long companyId, int status);
}

package com.dshop.datn.repositories;

import com.dshop.datn.models.PolicyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyImageRepository extends JpaRepository<PolicyImage, Long> {
	List<PolicyImage> findByPolicyId(long policyId);
	
	List<PolicyImage> findByStatus(int status);

}

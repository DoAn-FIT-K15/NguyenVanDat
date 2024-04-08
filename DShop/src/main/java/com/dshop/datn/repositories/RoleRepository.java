package com.dshop.datn.repositories;

import com.dshop.datn.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findById(long roleId);
}

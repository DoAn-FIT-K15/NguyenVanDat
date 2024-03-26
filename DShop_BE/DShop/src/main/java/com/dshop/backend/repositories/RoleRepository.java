package com.dshop.backend.repositories;

import com.dshop.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findById(long roleId);
}

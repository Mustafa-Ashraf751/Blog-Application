package com.BlogApplication.Bloging.application.api.repository;

import com.BlogApplication.Bloging.application.api.entity.role.Role;
import com.BlogApplication.Bloging.application.api.entity.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
  Optional<Role> findByName(RoleName name);
}

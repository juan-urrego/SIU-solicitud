package com.solicitud.solicitud.security.repository;

import com.solicitud.solicitud.security.entity.Role;
import com.solicitud.solicitud.security.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}

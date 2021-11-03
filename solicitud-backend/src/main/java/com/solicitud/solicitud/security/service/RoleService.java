package com.solicitud.solicitud.security.service;

import com.solicitud.solicitud.security.entity.Role;
import com.solicitud.solicitud.security.enums.RoleName;
import com.solicitud.solicitud.security.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }

    public void save(Role rol){
        roleRepository.save(rol);
    }
}

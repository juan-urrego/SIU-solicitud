package com.solicitud.solicitud.security.repository;

import com.solicitud.solicitud.security.entity.Role;
import com.solicitud.solicitud.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByActiveFalseAndEmail(String email);
    Optional<User> findByActiveAndRolesIn(boolean active, Iterable<Role> roles);
}

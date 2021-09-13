package com.solicitud.solicitud.security.repository;

import com.solicitud.solicitud.security.entity.Rol;
import com.solicitud.solicitud.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Usuario> findByActivoAndRolesIn(boolean activo, Set<Rol> roles);
    Optional<Usuario> findByFirma(String firma);
}

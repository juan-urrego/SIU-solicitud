package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Solicitud;
import com.solicitud.solicitud.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    List<Solicitud> findAllByUsuario(Usuario usuario);
    Optional<Solicitud> findByIdAndUsuario(int id, Usuario usuario);
}

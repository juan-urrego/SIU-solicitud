package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Estado;
import com.solicitud.solicitud.enums.EstadoNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer> {
    Optional<Estado> findByEstadoNombre(EstadoNombre estadoNombre);
}

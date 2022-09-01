package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    boolean existsByCodigoProyecto(String email);
}

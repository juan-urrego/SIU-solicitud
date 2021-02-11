package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
}

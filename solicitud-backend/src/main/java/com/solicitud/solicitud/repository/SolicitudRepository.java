package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
}

package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.DetalleTramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleTramiteRepository extends JpaRepository<DetalleTramite, Integer> {
}

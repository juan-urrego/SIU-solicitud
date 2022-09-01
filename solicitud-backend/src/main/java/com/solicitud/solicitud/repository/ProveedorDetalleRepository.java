package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.ProveedorDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorDetalleRepository extends JpaRepository<ProveedorDetalle, Integer> {
}

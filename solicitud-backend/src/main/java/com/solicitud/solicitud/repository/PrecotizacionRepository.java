package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Precotizacion;
import com.solicitud.solicitud.entity.Proveedor;
import com.solicitud.solicitud.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PrecotizacionRepository extends JpaRepository<Precotizacion, Integer> {
    Optional<Set<Precotizacion>> findAllBySolicitud(Solicitud solicitud);
    Optional<Precotizacion> findByProveedorAndSolicitudAndValorIvaAndValorTotal(Proveedor proveedor,Solicitud solicitud, int valorIva, int valorTotal);
}

package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.ParametroObservacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParametroObservacionRepository extends JpaRepository<ParametroObservacion, Integer> {

    Optional<ParametroObservacion> findByParametro (byte parametro);
}

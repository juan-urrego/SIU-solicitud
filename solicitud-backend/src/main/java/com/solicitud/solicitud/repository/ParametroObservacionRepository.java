package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.ParametroObservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametroObservacionRepository extends JpaRepository<ParametroObservacion, Integer> {

    Optional<ParametroObservacion> findByParametro (byte parametro);
}

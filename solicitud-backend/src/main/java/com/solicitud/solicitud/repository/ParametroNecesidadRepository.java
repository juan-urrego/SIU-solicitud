package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.ParametroNecesidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParametroNecesidadRepository extends JpaRepository<ParametroNecesidad, Integer> {

    Optional<ParametroNecesidad> findByParametro (byte parametro);
}

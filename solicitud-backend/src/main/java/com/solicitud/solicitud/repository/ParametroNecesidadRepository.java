package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.ParametroNecesidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametroNecesidadRepository extends JpaRepository<ParametroNecesidad, Integer> {

    Optional<ParametroNecesidad> findByParametro (byte parametro);
}

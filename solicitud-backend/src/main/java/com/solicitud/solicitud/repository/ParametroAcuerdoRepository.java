package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.ParametroAcuerdo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParametroAcuerdoRepository extends JpaRepository<ParametroAcuerdo, Integer> {

    Optional<ParametroAcuerdo> findByParametro (byte parametro);
}

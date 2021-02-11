package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.ParametroConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParametroConsultaRepository extends JpaRepository<ParametroConsulta, Integer> {

    Optional<ParametroConsulta> findByParametro (byte parametro);
}

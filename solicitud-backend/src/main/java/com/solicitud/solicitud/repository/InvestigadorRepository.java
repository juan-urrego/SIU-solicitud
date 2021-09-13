package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Investigador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestigadorRepository extends JpaRepository<Investigador, Integer> {
    boolean existsInvestigadorByIdentificacion (String identificacion);
}

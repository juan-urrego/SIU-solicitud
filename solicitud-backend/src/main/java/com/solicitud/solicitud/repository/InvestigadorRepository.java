package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Investigador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvestigadorRepository extends JpaRepository<Investigador, Integer> {
    boolean existsInvestigadorByIdentificacion(String identificacion);
    boolean existsByEmail(String email);
}

package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.ParametroAcuerdo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametroAcuerdoRepository extends JpaRepository<ParametroAcuerdo, Integer> {
    Optional<ParametroAcuerdo> findByParametro (byte parametro);
}

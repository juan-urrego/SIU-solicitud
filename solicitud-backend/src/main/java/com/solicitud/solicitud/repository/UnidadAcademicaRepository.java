package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.UnidadAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadAcademicaRepository extends JpaRepository<UnidadAcademica, Integer> {
}

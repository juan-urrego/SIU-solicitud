package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.LineaGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaGeneralRepository extends JpaRepository<LineaGeneral, Integer> {
}

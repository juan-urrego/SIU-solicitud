package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.LineaEspecifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaEspecificaRepository extends JpaRepository<LineaEspecifica, Integer> {
}

package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.GrupoInvestigador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoInvestigadorRepository extends JpaRepository<GrupoInvestigador, Integer> {
}

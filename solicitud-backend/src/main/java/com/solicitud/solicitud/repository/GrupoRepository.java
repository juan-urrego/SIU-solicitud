package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    boolean existsByCodigoGrupo(String codigoGrupo);
}

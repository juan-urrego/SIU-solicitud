package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    List<Consulta> findAllBySolicitud_Usuario(Usuario usuario);
    Optional<Consulta> findByIdAndSolicitud_Usuario(int id, Usuario usuario);
}

package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudioRepository extends JpaRepository<Estudio, Integer> {
    List<Estudio> findAllBySolicitud_Usuario(Usuario usuario);
    Optional<Estudio> findByIdAndSolicitud_Usuario(int id, Usuario usuario);
}

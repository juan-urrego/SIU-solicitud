package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Solicitud;
import com.solicitud.solicitud.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    List<Solicitud> findAllByUser(User user);
    Optional<Solicitud> findByIdAndUser(int id, User user);
}

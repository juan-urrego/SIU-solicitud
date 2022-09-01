package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.Argumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArgumentoRepository extends JpaRepository<Argumento, Integer> {
}

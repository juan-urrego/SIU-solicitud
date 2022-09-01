package com.solicitud.solicitud.repository;

import com.solicitud.solicitud.entity.ParametroArgumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametroArgumentoRepository extends JpaRepository<ParametroArgumento, Integer> {

}

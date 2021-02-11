package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.DetalleTramite;
import com.solicitud.solicitud.repository.DetalleTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleTramiteService {

    @Autowired
    DetalleTramiteRepository detalleTramiteRepository;

    public Optional<DetalleTramite> getOne(int id){
        return detalleTramiteRepository.findById(id);
    }

    public boolean existsById(final int id){
        return detalleTramiteRepository.existsById(id);
    }

    public List<DetalleTramite> getDetalleTramite(){
        final List<DetalleTramite> detalleTramites;
        detalleTramites = detalleTramiteRepository.findAll();
        return detalleTramites;
    }

    public void save(final DetalleTramite detalleTramite){
        detalleTramiteRepository.save(detalleTramite);
    }

    public void delete(int id){
        detalleTramiteRepository.deleteById(id);
    }

}

package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Solicitud;
import com.solicitud.solicitud.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SolicitudService {

    @Autowired
    SolicitudRepository solicitudRepository;

    public Optional<Solicitud> getOne(int id){
        return solicitudRepository.findById(id);
    }

    public boolean existsById(final int id){
        return solicitudRepository.existsById(id);
    }

    public List<Solicitud> getSolicitud(){
        final List<Solicitud> solicituds;
        solicituds = solicitudRepository.findAll();
        return solicituds;
    }

    public Solicitud save(final Solicitud solicitud){
        return solicitudRepository.save(solicitud);
    }

    public void delete(int id){
        solicitudRepository.deleteById(id);
    }

}
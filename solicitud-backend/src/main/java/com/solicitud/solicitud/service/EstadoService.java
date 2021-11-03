package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Estado;
import com.solicitud.solicitud.enums.EstadoNombre;
import com.solicitud.solicitud.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    public Optional<Estado> getOne(int id){
        return estadoRepository.findById(id);
    }

    public Optional<Estado> getByEstadoNombre(EstadoNombre estadoNombre){
        return estadoRepository.findByEstadoNombre(estadoNombre);
    }

    public List<Estado> getEstado(){
        final List<Estado> estados;
        estados = estadoRepository.findAll();
        return estados;
    }

    public void save(final Estado estado){
        estadoRepository.save(estado);
    }

    public void delete(int id){
        estadoRepository.deleteById(id);
    }

}

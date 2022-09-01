package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Estado;
import com.solicitud.solicitud.enums.EstadoNombre;
import com.solicitud.solicitud.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    public Estado getByEstadoNombre(EstadoNombre estadoNombre){
        return estadoRepository.findByEstadoNombre(estadoNombre).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "status not found"));
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

package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Proyecto;
import com.solicitud.solicitud.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    ProyectoRepository proyectoRepository;

    public Optional<Proyecto> getOne(int id){
        return proyectoRepository.findById(id);
    }

    public boolean existsById(final int id){
        return proyectoRepository.existsById(id);
    }

    public List<Proyecto> getProyecto(){
        final List<Proyecto> proyectos;
        proyectos = proyectoRepository.findAll();
        return proyectos;
    }

    public void save(final Proyecto proyecto){
        proyectoRepository.save(proyecto);
    }

    public void delete(int id){
        proyectoRepository.deleteById(id);
    }
}

package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Grupo;
import com.solicitud.solicitud.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GrupoService {

    @Autowired
    GrupoRepository grupoRepository;

    public Optional<Grupo> getOne(int id){
        return grupoRepository.findById(id);
    }

    public boolean existsById(final int id){
        return grupoRepository.existsById(id);
    }

    public List<Grupo> getGrupo(){
        final List<Grupo> grupos;
        grupos = grupoRepository.findAll();
        return grupos;
    }

    public void save(final Grupo grupo){
        grupoRepository.save(grupo);
    }

    public void delete(int id){
        grupoRepository.deleteById(id);
    }

}

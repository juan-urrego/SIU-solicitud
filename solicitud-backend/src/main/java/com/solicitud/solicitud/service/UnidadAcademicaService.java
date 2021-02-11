package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.UnidadAcademica;
import com.solicitud.solicitud.repository.UnidadAcademicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UnidadAcademicaService {

    @Autowired
    UnidadAcademicaRepository unidadAcademicaRepository;

    public Optional<UnidadAcademica> getOne(int id){
        return unidadAcademicaRepository.findById(id);
    }

    public boolean existsById(final int id){
        return unidadAcademicaRepository.existsById(id);
    }

    public List<UnidadAcademica> getUnidadAcademica(){
        final List<UnidadAcademica> unidadAcademicas;
        unidadAcademicas = unidadAcademicaRepository.findAll();
        return unidadAcademicas;
    }

    public void save(final UnidadAcademica unidadAcademica){
        unidadAcademicaRepository.save(unidadAcademica);
    }

    public void delete(int id){
        unidadAcademicaRepository.deleteById(id);
    }

}

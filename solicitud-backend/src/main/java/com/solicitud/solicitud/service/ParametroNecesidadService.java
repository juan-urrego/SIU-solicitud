package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.ParametroNecesidad;
import com.solicitud.solicitud.repository.ParametroNecesidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParametroNecesidadService {

    @Autowired
    ParametroNecesidadRepository parametroNecesidadRepository;

    public Optional<ParametroNecesidad> getOne(int id){
        return parametroNecesidadRepository.findById(id);
    }

    public boolean existsById(final int id){
        return parametroNecesidadRepository.existsById(id);
    }

    public Optional<ParametroNecesidad> getByParametro(final byte parametro){
        return parametroNecesidadRepository.findByParametro(parametro);
    }

    public List<ParametroNecesidad> getNecesidad(){
        final List<ParametroNecesidad> parametroNecesidades;
        parametroNecesidades = parametroNecesidadRepository.findAll();
        return parametroNecesidades;
    }

    public void save(final ParametroNecesidad parametroNecesidad){
        parametroNecesidadRepository.save(parametroNecesidad);
    }

    public void delete(int id){
        parametroNecesidadRepository.deleteById(id);
    }
}

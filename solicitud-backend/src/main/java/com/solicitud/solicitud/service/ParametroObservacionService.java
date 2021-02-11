package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.ParametroNecesidad;
import com.solicitud.solicitud.entity.ParametroObservacion;
import com.solicitud.solicitud.repository.ParametroNecesidadRepository;
import com.solicitud.solicitud.repository.ParametroObservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParametroObservacionService {

    @Autowired
    ParametroObservacionRepository parametroObservacionRepository;

    public Optional<ParametroObservacion> getOne(int id){
        return parametroObservacionRepository.findById(id);
    }

    public boolean existsById(final int id){
        return parametroObservacionRepository.existsById(id);
    }

    public Optional<ParametroObservacion> getByParametro(final byte parametro){
        return parametroObservacionRepository.findByParametro(parametro);
    }

    public List<ParametroObservacion> getObservacion(){
        final List<ParametroObservacion> parametroObservaciones;
        parametroObservaciones = parametroObservacionRepository.findAll();
        return parametroObservaciones;
    }

    public void save(final ParametroObservacion parametroObservacion){
        parametroObservacionRepository.save(parametroObservacion);
    }

    public void delete(int id){
        parametroObservacionRepository.deleteById(id);
    }
}

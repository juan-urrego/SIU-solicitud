package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Precotizacion;
import com.solicitud.solicitud.repository.PrecotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrecotizacionService {

    @Autowired
    PrecotizacionRepository precotizacionRepository;

    public Optional<Precotizacion> getOne(int id){
        return precotizacionRepository.findById(id);
    }

    public List<Precotizacion> getPrecotizacion(){
        final List<Precotizacion> precotizacions;
        precotizacions = precotizacionRepository.findAll();
        return precotizacions;
    }

    public void save(final Precotizacion precotizacion){
        precotizacionRepository.save(precotizacion);
    }

    public void delete(int id){
        precotizacionRepository.deleteById(id);
    }

}

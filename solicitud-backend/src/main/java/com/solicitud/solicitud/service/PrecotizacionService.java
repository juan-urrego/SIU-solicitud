package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Precotizacion;
import com.solicitud.solicitud.repository.PrecotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecotizacionService {

    @Autowired
    PrecotizacionRepository repository;

    public Precotizacion savePrecotizacion(Precotizacion precotizacion){
        return repository.save(precotizacion);
    }

    public List<Precotizacion> getPrecotizaciones(){
        return repository.findAll();
    }

    public Precotizacion getPrecotizacionById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deletePrecotizacion(int id){
        repository.deleteById(id);
        return "Precotizacion Eliminado " + id;
    }

    public Precotizacion updatePrecotizacion(Precotizacion newPrecotizacion, int id){
        return repository.findById(id)
                .map(precotizacion -> {
                    precotizacion.setProveedor(newPrecotizacion.getProveedor());
//                    precotizacion.setSolicitud(newPrecotizacion.getSolicitud());
                    precotizacion.setValor(newPrecotizacion.getValor());
                    return repository.save(precotizacion);
                })
                .orElse(null);
    }
}

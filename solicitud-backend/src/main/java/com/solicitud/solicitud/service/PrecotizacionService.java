package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Precotizacion;
import com.solicitud.solicitud.entity.Proveedor;
import com.solicitud.solicitud.entity.Solicitud;
import com.solicitud.solicitud.repository.PrecotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PrecotizacionService {

    @Autowired
    PrecotizacionRepository precotizacionRepository;

    public Optional<Precotizacion> getOne(int id){
        return precotizacionRepository.findById(id);
    }

    public Optional<Precotizacion> getByAllParametres(Proveedor proveedor, Solicitud solicitud, int valorIva, int valorTotal) {
        return precotizacionRepository.findByProveedorAndSolicitudAndValorIvaAndValorTotal(proveedor,solicitud,valorIva,valorTotal);
    }

    public Optional<Set<Precotizacion>> getAllBySolicitud(Solicitud solicitud) {
        return precotizacionRepository.findAllBySolicitud(solicitud);
    }

    public List<Precotizacion> getPrecotizacion(){
        final List<Precotizacion> precotizacions;
        precotizacions = precotizacionRepository.findAll();
        return precotizacions;
    }

    public void saveList(final Set<Precotizacion> precotizaciones) {
        precotizacionRepository.saveAll(precotizaciones);
    }

    public Precotizacion save(final Precotizacion precotizacion){
        return precotizacionRepository.save(precotizacion);
    }

    public void delete(int id){
        precotizacionRepository.deleteById(id);
    }

}

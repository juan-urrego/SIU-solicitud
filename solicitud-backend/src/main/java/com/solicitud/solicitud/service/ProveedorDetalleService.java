package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.GrupoInvestigador;
import com.solicitud.solicitud.entity.ProveedorDetalle;
import com.solicitud.solicitud.repository.GrupoInvestigadorRepository;
import com.solicitud.solicitud.repository.ProveedorDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProveedorDetalleService {

    @Autowired
    ProveedorDetalleRepository proveedorDetalleRepository;

    public Optional<ProveedorDetalle> getOne(int id){
        return proveedorDetalleRepository.findById(id);
    }

    public List<ProveedorDetalle> getProveedorDetalle(){
        final List<ProveedorDetalle> proveedorDetalles;
        proveedorDetalles = proveedorDetalleRepository.findAll();
        return proveedorDetalles;
    }

    public void save(final ProveedorDetalle proveedorDetalle){
        proveedorDetalleRepository.save(proveedorDetalle);
    }

    public void delete(int id){
        proveedorDetalleRepository.deleteById(id);
    }

}

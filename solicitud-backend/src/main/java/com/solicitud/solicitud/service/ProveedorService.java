package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Proveedor;
import com.solicitud.solicitud.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProveedorService {

    @Autowired
    ProveedorRepository proveedorRepository;

    public Optional<Proveedor> getOne(int id){
        return proveedorRepository.findById(id);
    }

    public boolean existsById(final int id){
        return proveedorRepository.existsById(id);
    }

    public List<Proveedor> getProveedor(){
        final List<Proveedor> proveedors;
        proveedors = proveedorRepository.findAll();
        return proveedors;
    }

    public void save(final Proveedor proveedor){
        proveedorRepository.save(proveedor);
    }

    public void delete(int id){
        proveedorRepository.deleteById(id);
    }

}

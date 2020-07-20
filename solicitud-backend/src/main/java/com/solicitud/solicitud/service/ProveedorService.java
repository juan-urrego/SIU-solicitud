package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Proveedor;
import com.solicitud.solicitud.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    ProveedorRepository repository;

    public Proveedor saveProveedor(Proveedor proveedor){
        return repository.save(proveedor);
    }

    public List<Proveedor> getProveedores(){
        return repository.findAll();
    }

    public Proveedor getProveedorById(int id){
        return repository.findById(id).orElse(null);
    }

    public String deleteProveedor(int id){
        repository.deleteById(id);
        return "Proveedor Eliminado " + id;
    }

    public Proveedor updateProveedor(Proveedor newProveedor, int id){
        return repository.findById(id)
                .map(proveedor -> {
                    proveedor.setNombre(newProveedor.getNombre());
                    proveedor.setNit(newProveedor.getNit());
                    proveedor.setTelefono((newProveedor.getTelefono()));
                    return repository.save(proveedor);
                })
                .orElse(null);
    }
}

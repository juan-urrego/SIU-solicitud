package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.ProveedorDto;
import com.solicitud.solicitud.entity.Proveedor;
import com.solicitud.solicitud.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ProveedorService {

    final
    ProveedorRepository proveedorRepository;

    @Autowired
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Proveedor getProveedorById(int id){
        return proveedorRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "provider does not exist with this id, or not found"));
    }


    public List<Proveedor> getAll(){
        return proveedorRepository.findAll();
    }

    public void save(Proveedor proveedor){
        proveedorRepository.save(proveedor);
    }

    public void saveProveedor(ProveedorDto proveedorDto) {
        Proveedor proveedor = new Proveedor(proveedorDto.getNombre(), proveedorDto.getIdentificacion(), proveedorDto.getTelefono(), proveedorDto.getCiudad(), proveedorDto.getTipo());
        save(proveedor);
    }

    public void update(int id, ProveedorDto proveedorDto) {
        Proveedor proveedor = getProveedorById(id);
        proveedor.setNombre(proveedorDto.getNombre());
        proveedor.setIdentificacion(proveedorDto.getIdentificacion());
        proveedor.setTelefono(proveedorDto.getTelefono());
        proveedor.setCiudad(proveedorDto.getCiudad());
        proveedor.setTipo(proveedorDto.getTipo());
        save(proveedor);
    }

    public void delete(int id){
        Proveedor proveedor = getProveedorById(id);
        proveedorRepository.delete(proveedor);
    }
}

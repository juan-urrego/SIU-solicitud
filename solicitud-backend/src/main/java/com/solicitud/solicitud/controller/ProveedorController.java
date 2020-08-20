package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.entity.Proveedor;
import com.solicitud.solicitud.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProveedorController {

    @Autowired
    ProveedorService service;

    @PostMapping("/proveedores/agregar")
    public Proveedor addProveedor(@RequestBody Proveedor proveedor){
        return  service.saveProveedor(proveedor);
    }

    @GetMapping("/proveedores")
    public List<Proveedor> findAllProveedor(){
        return  service.getProveedores();
    }

    @GetMapping("/proveedores/{id}")
    public Proveedor findProveedorById(@PathVariable int id){
        return service.getProveedorById(id);
    }


    @PutMapping("/proveedores/actualizar/{id}")
    public Proveedor updateProveedor(@RequestBody Proveedor proveedor, @PathVariable int id){
        return service.updateProveedor(proveedor, id);
    }

    @DeleteMapping("/proveedores/eliminar/{id}")
    public String deleteProveedor (@PathVariable int id){
        return service.deleteProveedor(id);
    }
}

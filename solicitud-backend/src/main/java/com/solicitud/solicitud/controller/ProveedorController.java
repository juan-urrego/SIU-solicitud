package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.ProveedorDto;
import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.entity.Proveedor;
import com.solicitud.solicitud.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin
public class ProveedorController {


    @Autowired
    ProveedorService proveedorService;

    @GetMapping("/proveedores")
    public ResponseEntity<List<Proveedor>> list(){
        List<Proveedor> list = proveedorService.getProveedor();
        return new ResponseEntity<List<Proveedor>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!proveedorService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un proveedor con esa id"), HttpStatus.NOT_FOUND);
        Proveedor proveedor = proveedorService.getOne(id).get();
        return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete (@PathVariable("id") int id){
        if(!proveedorService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un proveedor con esa id"), HttpStatus.NOT_FOUND);
        proveedorService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("Proveedor eliminado"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProveedorDto proveedorDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Mensaje>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        Proveedor proveedor = new Proveedor(proveedorDto.getNombre(), proveedorDto.getIdentificacion(), proveedorDto.getTelefono(), proveedorDto.getCiudad(), proveedorDto.getTipo());
        proveedorService.save(proveedor);
        return new ResponseEntity<Mensaje>(new Mensaje("Proveedor guardado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody ProveedorDto proveedorDto){
        if (!proveedorService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un proveedor con esa id"), HttpStatus.NOT_FOUND);
        Proveedor proveedor = proveedorService.getOne(id).get();
        proveedor.setNombre(proveedorDto.getNombre());
        proveedor.setIdentificacion(proveedorDto.getIdentificacion());
        proveedor.setTelefono(proveedorDto.getTelefono());
        proveedor.setCiudad(proveedorDto.getCiudad());
        proveedor.setTipo(proveedorDto.getTipo());
        proveedorService.save(proveedor);
        return new ResponseEntity<Mensaje>(new Mensaje("Proveedor actualizado"), HttpStatus.OK);
    }
}
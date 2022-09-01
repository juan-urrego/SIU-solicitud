package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.ProveedorDto;
import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.entity.Proveedor;
import com.solicitud.solicitud.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin
public class ProveedorController {


    final
    ProveedorService proveedorService;

    @Autowired
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("/proveedores")
    public ResponseEntity<List<Proveedor>> list(){
        List<Proveedor> list = proveedorService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getById(@PathVariable(value = "id") int id){
        Proveedor proveedor = proveedorService.getProveedorById(id);
        return new ResponseEntity<>(proveedor, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable(value = "id") int id){
        proveedorService.delete(id);
        return new ResponseEntity<>(new Message("provider deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody ProveedorDto proveedorDto){
        proveedorService.saveProveedor(proveedorDto);
        return new ResponseEntity<>(new Message("provider created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody ProveedorDto proveedorDto){
        proveedorService.update(id, proveedorDto);
        return new ResponseEntity<>(new Message("provider updated"), HttpStatus.OK);
    }
}
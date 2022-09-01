package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.ProyectoDto;
import com.solicitud.solicitud.entity.Proyecto;
import com.solicitud.solicitud.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin
public class ProyectoController {

    final
    ProyectoService proyectoService;

    @Autowired
    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = proyectoService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable(value = "id") int id){
        Proyecto proyecto = proyectoService.getProyectoById(id);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable(value = "id") int id){
        proyectoService.delete(id);
        return new ResponseEntity<>(new Message("projects deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody ProyectoDto proyectoDto){
        proyectoService.saveProyecto(proyectoDto);
        return new ResponseEntity<>(new Message("project created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody ProyectoDto proyectoDto){
        proyectoService.update(id, proyectoDto);
        return new ResponseEntity<>(new Message("project updated"), HttpStatus.OK);
    }
}

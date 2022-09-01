package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroNecesidad;
import com.solicitud.solicitud.service.ParametroNecesidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parametro/necesidad")
@CrossOrigin
public class ParametroNecesidadController {

    final
    ParametroNecesidadService parametroNecesidadService;

    @Autowired
    public ParametroNecesidadController(ParametroNecesidadService parametroNecesidadService) {
        this.parametroNecesidadService = parametroNecesidadService;
    }

    @GetMapping("/necesidades")
    public ResponseEntity<List<ParametroNecesidad>> list(){
        List<ParametroNecesidad> list = parametroNecesidadService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/necesidades/selected")
    public ResponseEntity<ParametroNecesidad> getByParametro(){
        ParametroNecesidad parametroNecesidad = parametroNecesidadService.getParametroActivo((byte) 1);
        return new ResponseEntity<>(parametroNecesidad, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") int id){
        ParametroNecesidad parametroNecesidad = parametroNecesidadService.getParametroNecesidadById(id);
        return new ResponseEntity<ParametroNecesidad>(parametroNecesidad, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable("id") int id){
        parametroNecesidadService.delete(id);
        return new ResponseEntity<>(new Message("necessity parameter deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ParametroDto parametroDto){
        parametroNecesidadService.saveParametro(parametroDto);
        return new ResponseEntity<>(new Message("necessity parameter created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody ParametroDto parametroDto){
        parametroNecesidadService.update(id, parametroDto);
        return new ResponseEntity<>(new Message("necessity parameter updated"), HttpStatus.OK);
    }

    @PutMapping("/update/selected/{id}")
    public ResponseEntity<Message> updateSelected(@PathVariable(value = "id") int id){
        parametroNecesidadService.activeParameter(id);
        return new ResponseEntity<>(new Message("necessity parameter modified"), HttpStatus.OK);
    }

    @PutMapping("/delete/selected")
    public ResponseEntity<Message> deleteSelected() {
        parametroNecesidadService.disableParameterActive();
        return new ResponseEntity<>(new Message("necessity parameter modified"), HttpStatus.OK);
    }
}

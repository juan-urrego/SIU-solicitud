package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroObservacion;
import com.solicitud.solicitud.service.ParametroObservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parametro/observacion")
@CrossOrigin
public class ParametroObservacionController {

    final
    ParametroObservacionService parametroObservacionService;

    @Autowired
    public ParametroObservacionController(ParametroObservacionService parametroObservacionService) {
        this.parametroObservacionService = parametroObservacionService;
    }

    @GetMapping("/observaciones")
    public ResponseEntity<List<ParametroObservacion>> list(){
        List<ParametroObservacion> list = parametroObservacionService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/observaciones/selected")
    public ResponseEntity<ParametroObservacion> getByParametro(){
        ParametroObservacion parametroObservacion = parametroObservacionService.getParametroActivo((byte) 1);
        return new ResponseEntity<>(parametroObservacion, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        ParametroObservacion parametroObservacion = parametroObservacionService.getParametroObservacionById(id);
        return new ResponseEntity<>(parametroObservacion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable(value = "id") int id){
        parametroObservacionService.delete(id);
        return new ResponseEntity<>(new Message("observation parameter deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ParametroDto parametroDto){
        parametroObservacionService.saveParametro(parametroDto);
        return new ResponseEntity<>(new Message("observation parameter created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody ParametroDto parametroDto){
        parametroObservacionService.update(id, parametroDto);
        return new ResponseEntity<>(new Message("observation parameter updated"), HttpStatus.OK);
    }

    @PutMapping("/update/selected/{id}")
    public ResponseEntity<Message> updateSelected(@PathVariable(value = "id") int id){
        parametroObservacionService.activeParameter(id);
        return new ResponseEntity<>(new Message("observation parameter modified"), HttpStatus.OK);
    }

    @PutMapping("/delete/selected")
    public ResponseEntity<Message> deleteSelected() {
        parametroObservacionService.disableParameterActive();
        return new ResponseEntity<>(new Message("observation parameter modified"), HttpStatus.OK);
    }
}

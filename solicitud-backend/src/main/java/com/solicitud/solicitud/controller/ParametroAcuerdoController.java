package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroAcuerdo;
import com.solicitud.solicitud.service.ParametroAcuerdoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parametro/acuerdo")
@CrossOrigin
public class ParametroAcuerdoController {

    final
    ParametroAcuerdoService parametroAcuerdoService;

    @Autowired
    public ParametroAcuerdoController(ParametroAcuerdoService parametroAcuerdoService) {
        this.parametroAcuerdoService = parametroAcuerdoService;
    }

    @GetMapping("/acuerdos")
    public ResponseEntity<List<ParametroAcuerdo>> list(){
        List<ParametroAcuerdo> list = parametroAcuerdoService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/acuerdos/selected")
    public ResponseEntity<ParametroAcuerdo> getParametroActivo(){
        ParametroAcuerdo parametroAcuerdo = parametroAcuerdoService.getParametroActivo((byte) 1);
        return new ResponseEntity<>(parametroAcuerdo, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParametroAcuerdo> getById(@PathVariable(value = "id") int id){
        ParametroAcuerdo parametroAcuerdo = parametroAcuerdoService.getParametroAcuerdoById(id);
        return new ResponseEntity<>(parametroAcuerdo, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable(value = "id") int id){
        parametroAcuerdoService.delete(id);
        return new ResponseEntity<Message>(new Message("agreement parameter deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody ParametroDto parametroDto){
        parametroAcuerdoService.saveParametro(parametroDto);
        return new ResponseEntity<>(new Message("agreement parameter created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody ParametroDto parametroDto){
        parametroAcuerdoService.update(id, parametroDto);
        return new ResponseEntity<>(new Message("agreement parameter updated"), HttpStatus.OK);
    }

    @PutMapping("/update/selected/{id}")
    public ResponseEntity<Message> updateSelected(@PathVariable(value = "id") int id){
        parametroAcuerdoService.activeParameter(id);
        return new ResponseEntity<>(new Message("agreement parameter modified"), HttpStatus.OK);
    }

    @PutMapping("/delete/selected")
    public ResponseEntity<Message> deleteSelected() {
        parametroAcuerdoService.disableParameterActive();
        return new ResponseEntity<>(new Message("agreement parameter modified"), HttpStatus.OK);
    }
}

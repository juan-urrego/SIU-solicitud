package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroConsulta;
import com.solicitud.solicitud.service.ParametroConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parametro/consulta")
@CrossOrigin
public class ParametroConsultaController {

    final
    ParametroConsultaService parametroConsultaService;

    @Autowired
    public ParametroConsultaController(ParametroConsultaService parametroConsultaService) {
        this.parametroConsultaService = parametroConsultaService;
    }

    @GetMapping("/consultas")
    public ResponseEntity<List<ParametroConsulta>> list(){
        List<ParametroConsulta> list = parametroConsultaService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/consultas/selected")
    public ResponseEntity<ParametroConsulta> getByParametro(){
        ParametroConsulta parametroConsulta = parametroConsultaService.getParametroActivo((byte) 1);
        return new ResponseEntity<>(parametroConsulta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParametroConsulta> getById(@PathVariable(value = "id") int id){
        ParametroConsulta parametroConsulta = parametroConsultaService.getParametroConsultaById(id);
        return new ResponseEntity<>(parametroConsulta, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable(value = "id") int id){
        parametroConsultaService.delete(id);
        return new ResponseEntity<>(new Message("consult parameter deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody ParametroDto parametroDto){
        parametroConsultaService.saveParametro(parametroDto);
        return new ResponseEntity<>(new Message("consult parameter created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody ParametroDto parametroDto){
        parametroConsultaService.update(id, parametroDto);
        return new ResponseEntity<>(new Message("consult parameter updated"), HttpStatus.OK);
    }

    @PutMapping("/update/selected/{id}")
    public ResponseEntity<Message> updateSelected(@PathVariable("id") int id){
        parametroConsultaService.activeParameter(id);
        return new ResponseEntity<>(new Message("consult parameter modified"), HttpStatus.OK);
    }

    @PutMapping("/delete/selected")
    public ResponseEntity<Message> deleteSelected() {
        parametroConsultaService.disableParameterActive();
        return new ResponseEntity<>(new Message("consult parameter modified"), HttpStatus.OK);
    }
}

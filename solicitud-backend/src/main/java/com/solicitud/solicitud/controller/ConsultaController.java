package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping("/consultas/agregar")
    public Consulta addConsulta(@RequestBody Consulta consulta){
        return  service.saveConsulta(consulta);
    }

    @GetMapping("/consultas")
    public List<Consulta> findAllConsulta(){
        return  service.getConsultas();
    }

    @GetMapping("/consultas/{id}")
    public Consulta findConsultaById(@PathVariable int id){
        return service.getConsultaById(id);
    }

    @PutMapping("/consultas/actualizar/{id}")
    public Consulta updateConsulta(@RequestBody Consulta consulta, @PathVariable int id){
        return service.updateConsulta(consulta,id);
    }

    @DeleteMapping("/consultas/eliminar/{id}")
    public String deleteConsulta (@PathVariable int id){
        return service.deleteConsulta(id);
    }
}
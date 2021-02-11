package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.dto.ConsultaDto;
import com.solicitud.solicitud.entity.*;
import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.enums.EstadoNombre;
import com.solicitud.solicitud.service.*;
import com.solicitud.solicitud.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
@CrossOrigin
public class ConsultaController {


    @Autowired
    ConsultaService consultaService;

    @Autowired
    EstadoService estadoService;


    @GetMapping("/consultas")
    public ResponseEntity<List<Consulta>> list(){
        List<Consulta> list = consultaService.getConsulta();
        return new ResponseEntity<List<Consulta>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!consultaService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una consulta con esa id"), HttpStatus.NOT_FOUND);
        Consulta consulta = consultaService.getOne(id).get();
        return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody ConsultaDto consultaDto){
        if (!consultaService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una consulta con esa id"), HttpStatus.NOT_FOUND);
        Consulta consulta = consultaService.getOne(id).get();
        consulta.setParametro(consultaDto.getParametro());
        consultaService.save(consulta);
        return new ResponseEntity<Mensaje>(new Mensaje("Consulta actualizada"), HttpStatus.OK);
    }

    @PostMapping("/confirmar/{id}")
    public ResponseEntity<Mensaje> confirmar(@PathVariable int id){
        if (!consultaService.existsById(id))
            new ResponseEntity<Mensaje>(new Mensaje("No existe una consulta con esa id"), HttpStatus.NOT_FOUND);
        Consulta consulta = consultaService.getOne(id).get();
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.VERIFICADA).get();
        consulta.setEstado(estado);
        consultaService.save(consulta);
        return new ResponseEntity<Mensaje>(new Mensaje("Solicitud verificada correctamente"), HttpStatus.OK);
    }
}
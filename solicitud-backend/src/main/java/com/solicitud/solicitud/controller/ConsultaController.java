package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.ConsultaDto;
import com.solicitud.solicitud.entity.*;
import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.enums.EstadoNombre;
import com.solicitud.solicitud.security.service.UserService;
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

    @Autowired
    UserService userService;


    @GetMapping("/consultas")
    public ResponseEntity<List<Consulta>> list(){
        List<Consulta> list = consultaService.getConsultas();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!consultaService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe una consulta con esa id"), HttpStatus.NOT_FOUND);
        if(!consultaService.getOne(id).isPresent())
            return new ResponseEntity<Message>(new Message("No tiene acceso a esta consulta"), HttpStatus.FORBIDDEN);
        Consulta consulta = consultaService.getOne(id).get();
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") int id, @RequestBody ConsultaDto consultaDto , BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Campos mal puestos") , HttpStatus.BAD_REQUEST);
        if (!consultaService.existsById(id))
            return new ResponseEntity<>(new Message("No existe una consulta con esa id"), HttpStatus.NOT_FOUND);
        Consulta consulta = consultaService.getOne(id).orElse(null);
        consulta.setParametro(consultaDto.getParametro());
        consultaService.save(consulta);
        return new ResponseEntity<>(new Message("Consulta actualizada"), HttpStatus.OK);
    }

    @PostMapping("/confirmar/{id}")
    public ResponseEntity<Message> confirmar(@PathVariable int id, @RequestBody ConsultaDto consultaDto, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Campos mal puestos") , HttpStatus.BAD_REQUEST);
        if (!consultaService.existsById(id))
            return new ResponseEntity<>(new Message("No existe una consulta con esa id"), HttpStatus.NOT_FOUND);
        Consulta consulta = consultaService.getOne(id).orElse(null);
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.VERIFICADA).orElse(null);
        consulta.setParametro(consultaDto.getParametro());
        consulta.setEstado(estado);
        consultaService.save(consulta);
        return new ResponseEntity<>(new Message("Consulta de precios verificada correctamente"), HttpStatus.OK);
    }
}
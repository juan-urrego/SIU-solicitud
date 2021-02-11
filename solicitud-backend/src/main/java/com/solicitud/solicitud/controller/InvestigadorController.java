package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.InvestigadorDto;
import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.service.InvestigadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investigador")
@CrossOrigin
public class InvestigadorController {


    @Autowired
    InvestigadorService investigadorService;

    @GetMapping("/investigadores")
    public ResponseEntity<List<Investigador>> list(){
        List<Investigador> list = investigadorService.getInvestigador();
        return new ResponseEntity<List<Investigador>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!investigadorService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un investigador con esa id"), HttpStatus.NOT_FOUND);
        Investigador investigador = investigadorService.getOne(id).get();
        return new ResponseEntity<Investigador>(investigador, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete (@PathVariable("id") int id){
        if(!investigadorService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un investigador con esa id"), HttpStatus.NOT_FOUND);
        investigadorService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("Investigador eliminado"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody InvestigadorDto investigadorDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Mensaje>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        Investigador investigador = new Investigador(investigadorDto.getIdentificacion(), investigadorDto.getNombre(), investigadorDto.getTelefono(), investigadorDto.getEmail(), investigadorDto.getFirma());
        investigadorService.save(investigador);
        return new ResponseEntity<Mensaje>(new Mensaje("Investigador guardado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody InvestigadorDto investigadorDto){
        if (!investigadorService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un investigador con esa id"), HttpStatus.NOT_FOUND);
        Investigador investigador = investigadorService.getOne(id).get();
        investigador.setIdentificacion(investigadorDto.getIdentificacion());
        investigador.setNombre(investigadorDto.getNombre());
        investigador.setTelefono(investigadorDto.getTelefono());
        investigador.setEmail(investigadorDto.getEmail());
        investigador.setFirma(investigadorDto.getFirma());
        investigadorService.save(investigador);
        return new ResponseEntity<Mensaje>(new Mensaje("Investigador actualizado"), HttpStatus.OK);
    }
}
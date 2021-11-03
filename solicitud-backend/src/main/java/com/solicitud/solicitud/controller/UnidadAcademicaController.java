package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.UnidadAcademica;
import com.solicitud.solicitud.service.UnidadAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidadAcademica")
@CrossOrigin
public class UnidadAcademicaController {


    @Autowired
    UnidadAcademicaService unidadAcademicaService;

    @GetMapping("/unidadAcademicas")
    public ResponseEntity<List<UnidadAcademica>> list(){
        List<UnidadAcademica> list = unidadAcademicaService.getUnidadAcademica();
        return new ResponseEntity<List<UnidadAcademica>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!unidadAcademicaService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe una unidadAcademica con esa id"), HttpStatus.NOT_FOUND);
        UnidadAcademica unidadAcademica = unidadAcademicaService.getOne(id).get();
        return new ResponseEntity<UnidadAcademica>(unidadAcademica, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable("id") int id){
        if(!unidadAcademicaService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe una unidadAcademica con esa id"), HttpStatus.NOT_FOUND);
        unidadAcademicaService.delete(id);
        return new ResponseEntity<Message>(new Message("UnidadAcademica eliminada"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ParametroDto parametroDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Message>(new Message("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        UnidadAcademica unidadAcademica = new UnidadAcademica(parametroDto.getDescripcion());
        unidadAcademicaService.save(unidadAcademica);
        return new ResponseEntity<Message>(new Message("UnidadAcademica guardada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") int id, @RequestBody ParametroDto parametroDto){
        if (!unidadAcademicaService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un unidadAcademica con esa id"), HttpStatus.NOT_FOUND);
        UnidadAcademica unidadAcademica = unidadAcademicaService.getOne(id).get();
        unidadAcademica.setDescripcion(parametroDto.getDescripcion());
        unidadAcademicaService.save(unidadAcademica);
        return new ResponseEntity<Message>(new Message("UnidadAcademica actualizado"), HttpStatus.OK);
    }
}
package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.UnidadAcademica;
import com.solicitud.solicitud.service.UnidadAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidadAcademica")
@CrossOrigin
public class UnidadAcademicaController {

    final
    UnidadAcademicaService unidadAcademicaService;

    @Autowired
    public UnidadAcademicaController(UnidadAcademicaService unidadAcademicaService) {
        this.unidadAcademicaService = unidadAcademicaService;
    }

    @GetMapping("/unidadAcademicas")
    public ResponseEntity<List<UnidadAcademica>> list(){
        List<UnidadAcademica> list = unidadAcademicaService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadAcademica> getById(@PathVariable(value = "id") int id){
        UnidadAcademica unidadAcademica = unidadAcademicaService.getUnidadAcademicaById(id);
        return new ResponseEntity<>(unidadAcademica, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable("id") int id){
        unidadAcademicaService.delete(id);
        return new ResponseEntity<>(new Message("unit deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody ParametroDto parametroDto){
        unidadAcademicaService.saveUnidadAcademica(parametroDto);
        return new ResponseEntity<>(new Message("unit created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") int id, @RequestBody ParametroDto parametroDto){
        unidadAcademicaService.update(id, parametroDto);
        return new ResponseEntity<>(new Message("unit updated"), HttpStatus.OK);
    }
}
package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.dto.LineaGeneralDto;
import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.entity.LineaEspecifica;
import com.solicitud.solicitud.entity.LineaGeneral;
import com.solicitud.solicitud.service.LineaGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/lineaGeneral")
@CrossOrigin
public class LineaGeneralController {


    @Autowired
    LineaGeneralService lineaGeneralService;

    @GetMapping("/lineasGenerales")
    public ResponseEntity<List<LineaGeneral>> list(){
        List<LineaGeneral> list = lineaGeneralService.getLineaGeneral();
        return new ResponseEntity<List<LineaGeneral>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!lineaGeneralService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe una linea general con esa id"), HttpStatus.NOT_FOUND);
        LineaGeneral lineaGeneral = lineaGeneralService.getOne(id).get();
        return new ResponseEntity<LineaGeneral>(lineaGeneral, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable("id") int id){
        if(!lineaGeneralService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe una linea general con esa id"), HttpStatus.NOT_FOUND);
        lineaGeneralService.delete(id);
        return new ResponseEntity<Message>(new Message("linea general eliminada"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody() LineaGeneralDto lineaGeneralDto) {
        LineaGeneral lineaGeneral = new LineaGeneral(lineaGeneralDto.getNombre());
        Set<LineaEspecifica> lineaEspecificas = new HashSet<>();
        lineaGeneralDto.getLineaEspecificas().forEach(lineaEspecificax -> {
            LineaEspecifica lineaEspecifica = new LineaEspecifica(lineaEspecificax.getNombre(), lineaGeneral);
            lineaEspecificas.add(lineaEspecifica);
        });
        lineaGeneral.setLineaEspecificas(lineaEspecificas);
        lineaGeneralService.save(lineaGeneral);
        return new ResponseEntity<>(new Message("Linea general guardada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") int id, @RequestBody LineaGeneralDto lineaGeneralDto){
        if (!lineaGeneralService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un linea general con esa id"), HttpStatus.NOT_FOUND);
        LineaGeneral lineaGeneral = lineaGeneralService.getOne(id).get();
        lineaGeneral.setNombre(lineaGeneralDto.getNombre());
        Set<LineaEspecifica> lineaEspecificas = new HashSet<>();
        lineaGeneralDto.getLineaEspecificas().forEach(lineaEspecificax -> {
            LineaEspecifica lineaEspecifica = new LineaEspecifica(lineaEspecificax.getNombre(), lineaGeneral);
            lineaEspecificas.add(lineaEspecifica);
        });
        lineaGeneral.setLineaEspecificas(lineaEspecificas);
        lineaGeneralService.save(lineaGeneral);
        return new ResponseEntity<Message>(new Message("linea general actualizado"), HttpStatus.OK);
    }
}

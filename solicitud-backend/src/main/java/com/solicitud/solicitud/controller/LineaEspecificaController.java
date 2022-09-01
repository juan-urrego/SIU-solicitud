package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.LineaEspecificaDto;
import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.entity.LineaEspecifica;
import com.solicitud.solicitud.service.LineaEspecificaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lineaEspecifica")
@CrossOrigin
public class LineaEspecificaController {

    final
    LineaEspecificaService lineaEspecificaService;

    @Autowired
    public LineaEspecificaController(LineaEspecificaService lineaEspecificaService) {
        this.lineaEspecificaService = lineaEspecificaService;
    }

    @GetMapping("/lineasEspecificas")
    public ResponseEntity<List<LineaEspecifica>> list(){
        List<LineaEspecifica> list = lineaEspecificaService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LineaEspecifica> getById(@PathVariable(value = "id") int id){
        LineaEspecifica lineaEspecifica = lineaEspecificaService.getLineaEspecificaById(id);
        return new ResponseEntity<>(lineaEspecifica, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable(value = "id") int id){
        lineaEspecificaService.delete(id);
        return new ResponseEntity<>(new Message("Linea especifica eliminada"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody LineaEspecificaDto lineaEspecificaDto) {
        lineaEspecificaService.saveLinea(lineaEspecificaDto);
        return new ResponseEntity<>(new Message("specific line created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody LineaEspecificaDto lineaEspecificaDto){
        lineaEspecificaService.update(id, lineaEspecificaDto);
        return new ResponseEntity<>(new Message("specific line updated"), HttpStatus.OK);
    }
}

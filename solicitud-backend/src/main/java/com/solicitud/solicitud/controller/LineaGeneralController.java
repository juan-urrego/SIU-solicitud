package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.dto.LineaGeneralDto;
import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.entity.LineaGeneral;
import com.solicitud.solicitud.service.LineaGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lineaGeneral")
@CrossOrigin
public class LineaGeneralController {

    final
    LineaGeneralService lineaGeneralService;

    @Autowired
    public LineaGeneralController(LineaGeneralService lineaGeneralService) {
        this.lineaGeneralService = lineaGeneralService;
    }

    @GetMapping("/lineasGenerales")
    public ResponseEntity<List<LineaGeneral>> list(){
        List<LineaGeneral> list = lineaGeneralService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") int id){
        LineaGeneral lineaGeneral = lineaGeneralService.getLineaGeneralById(id);
        return new ResponseEntity<>(lineaGeneral, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable(value = "id") int id){
        lineaGeneralService.delete(id);
        return new ResponseEntity<>(new Message("general line deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody LineaGeneralDto lineaGeneralDto) {
        lineaGeneralService.saveLineaGeneral(lineaGeneralDto);
        return new ResponseEntity<>(new Message("general line created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody LineaGeneralDto lineaGeneralDto){
        lineaGeneralService.update(id, lineaGeneralDto);
        return new ResponseEntity<>(new Message("general line updated"), HttpStatus.OK);
    }
}

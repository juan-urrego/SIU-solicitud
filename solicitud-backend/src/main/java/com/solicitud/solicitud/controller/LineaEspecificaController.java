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

    @Autowired
    LineaEspecificaService lineaEspecificaService;

    @GetMapping("/lineasEspecificas")
    public ResponseEntity<List<LineaEspecifica>> list(){
        List<LineaEspecifica> list = lineaEspecificaService.getLineaEspecifica();
        return new ResponseEntity<List<LineaEspecifica>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!lineaEspecificaService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe una LineaEspecifica con esa id"), HttpStatus.NOT_FOUND);
        LineaEspecifica lineaEspecifica = lineaEspecificaService.getOne(id).get();
        return new ResponseEntity<LineaEspecifica>(lineaEspecifica, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable("id") int id){
        if(!lineaEspecificaService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe una LineaEspecifica con esa id"), HttpStatus.NOT_FOUND);
        lineaEspecificaService.delete(id);
        return new ResponseEntity<Message>(new Message("Linea especifica eliminada"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody() LineaEspecificaDto lineaEspecificaDto) {
        LineaEspecifica lineaEspecifica = new LineaEspecifica(lineaEspecificaDto.getNombre(), null
        );
        lineaEspecificaService.save(lineaEspecifica);
        return new ResponseEntity<>(new Message("Linea especifica guardada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") int id, @RequestBody LineaEspecificaDto lineaEspecificaDto){
        if (!lineaEspecificaService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un linea especifica con esa id"), HttpStatus.NOT_FOUND);
        LineaEspecifica lineaEspecifica = lineaEspecificaService.getOne(id).get();
        lineaEspecifica.setNombre(lineaEspecificaDto.getNombre());
        lineaEspecificaService.save(lineaEspecifica);
        return new ResponseEntity<Message>(new Message("Linea especifica actualizado"), HttpStatus.OK);
    }
}

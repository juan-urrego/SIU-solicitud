package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.LineaEspecificaDto;
import com.solicitud.solicitud.dto.LineaGeneralDto;
import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.entity.LineaEspecifica;
import com.solicitud.solicitud.entity.LineaGeneral;
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
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una LineaEspecifica con esa id"), HttpStatus.NOT_FOUND);
        LineaEspecifica lineaEspecifica = lineaEspecificaService.getOne(id).get();
        return new ResponseEntity<LineaEspecifica>(lineaEspecifica, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete (@PathVariable("id") int id){
        if(!lineaEspecificaService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una LineaEspecifica con esa id"), HttpStatus.NOT_FOUND);
        lineaEspecificaService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("Linea especifica eliminada"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody() LineaEspecificaDto lineaEspecificaDto) {
        LineaEspecifica lineaEspecifica = new LineaEspecifica(lineaEspecificaDto.getNombre(), null
        );
        lineaEspecificaService.save(lineaEspecifica);
        return new ResponseEntity<>(new Mensaje("Linea especifica guardada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody LineaEspecificaDto lineaEspecificaDto){
        if (!lineaEspecificaService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un linea especifica con esa id"), HttpStatus.NOT_FOUND);
        LineaEspecifica lineaEspecifica = lineaEspecificaService.getOne(id).get();
        lineaEspecifica.setNombre(lineaEspecificaDto.getNombre());
        lineaEspecificaService.save(lineaEspecifica);
        return new ResponseEntity<Mensaje>(new Mensaje("Linea especifica actualizado"), HttpStatus.OK);
    }
}

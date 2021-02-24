package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroObservacion;
import com.solicitud.solicitud.service.ParametroObservacionService;
import com.solicitud.solicitud.service.ParametroObservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parametro/observacion")
@CrossOrigin
public class ParametroObservacionController {

    @Autowired
    ParametroObservacionService parametroObservacionService;

    @GetMapping("/observaciones")
    public ResponseEntity<List<ParametroObservacion>> list(){
        List<ParametroObservacion> list = parametroObservacionService.getObservacion();
        return new ResponseEntity<List<ParametroObservacion>>(list, HttpStatus.OK);
    }

    @GetMapping("/observaciones/selected")
    public ResponseEntity<ParametroObservacion> getByParametro(){
        ParametroObservacion parametroObservacion = parametroObservacionService.getByParametro((byte) 1).get();
        return new ResponseEntity<ParametroObservacion>(parametroObservacion, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!parametroObservacionService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un parametro-observacion con esa id"), HttpStatus.NOT_FOUND);
        ParametroObservacion parametroObservacion = parametroObservacionService.getOne(id).get();
        return new ResponseEntity<ParametroObservacion>(parametroObservacion, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete (@PathVariable("id") int id){
        if(!parametroObservacionService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un parametro-observacion con esa id"), HttpStatus.NOT_FOUND);
        parametroObservacionService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("parametro-observacion eliminado"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ParametroDto parametroDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Mensaje>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        ParametroObservacion parametroObservacion= new ParametroObservacion(parametroDto.getDescripcion(), (byte) 0);
        if (parametroObservacionService.getObservacion().isEmpty())
            parametroObservacion.setParametro((byte) 1);
        parametroObservacionService.save(parametroObservacion);
        return new ResponseEntity<Mensaje>(new Mensaje("Parametro-observacion guardado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody ParametroDto parametroDto){
        if (!parametroObservacionService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un parametro-observacion con esa id"), HttpStatus.NOT_FOUND);
        ParametroObservacion parametroObservacion = parametroObservacionService.getOne(id).get();
        parametroDto.setDescripcion(parametroDto.getDescripcion());
        parametroObservacionService.save(parametroObservacion);
        return new ResponseEntity<Mensaje>(new Mensaje("Parametro-observacion actualizado"), HttpStatus.OK);
    }

    @PutMapping("/update/selected/{id}")
    public ResponseEntity<Mensaje> updateSelected(@PathVariable("id") int id){
        if (!parametroObservacionService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un parametro-observacion con esa id"), HttpStatus.NOT_FOUND);
        ParametroObservacion parametroObservacionActivo = parametroObservacionService.getByParametro((byte) 1).get();
        parametroObservacionActivo.setParametro((byte) 0);
        parametroObservacionService.save(parametroObservacionActivo);
        ParametroObservacion parametroObservacion = parametroObservacionService.getOne(id).get();
        parametroObservacion.setParametro((byte) 1);
        parametroObservacionService.save(parametroObservacion);
        return new ResponseEntity<Mensaje>(new Mensaje("Parametro-necesidad actualizado"), HttpStatus.OK);
    }
}

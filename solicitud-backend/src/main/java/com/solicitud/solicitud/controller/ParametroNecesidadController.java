package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroNecesidad;
import com.solicitud.solicitud.service.ParametroNecesidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parametro/necesidad")
@CrossOrigin
public class ParametroNecesidadController {

    @Autowired
    ParametroNecesidadService parametroNecesidadService;

    @GetMapping("/necesidades")
    public ResponseEntity<List<ParametroNecesidad>> list(){
        List<ParametroNecesidad> list = parametroNecesidadService.getNecesidad();
        if (list.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.OK);
        return new ResponseEntity<List<ParametroNecesidad>>(list, HttpStatus.OK);
    }

    @GetMapping("/necesidades/selected")
    public ResponseEntity<ParametroNecesidad> getByParametro(){
        ParametroNecesidad parametroNecesidad = parametroNecesidadService.getByParametro((byte) 1).orElse(null);
        if (parametroNecesidad == null){
            parametroNecesidad = new ParametroNecesidad("",(byte) 0);
            return  new ResponseEntity<ParametroNecesidad>(parametroNecesidad, HttpStatus.OK);
        }
        return new ResponseEntity<ParametroNecesidad>(parametroNecesidad, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!parametroNecesidadService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un parametro-necesidad con esa id"), HttpStatus.NOT_FOUND);
        ParametroNecesidad parametroNecesidad = parametroNecesidadService.getOne(id).get();
        return new ResponseEntity<ParametroNecesidad>(parametroNecesidad, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable("id") int id){
        if(!parametroNecesidadService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un parametro-necesidad con esa id"), HttpStatus.NOT_FOUND);
        parametroNecesidadService.delete(id);
        return new ResponseEntity<Message>(new Message("parametro-necesidad eliminado"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ParametroDto parametroDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Message>(new Message("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        ParametroNecesidad parametroNecesidad= new ParametroNecesidad(parametroDto.getDescripcion(), (byte) 0);
        if (parametroNecesidadService.getNecesidad().isEmpty())
            parametroNecesidad.setParametro((byte) 1);
        parametroNecesidadService.save(parametroNecesidad);
        return new ResponseEntity<Message>(new Message("Parametro-necesidad guardado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") int id, @RequestBody ParametroDto parametroDto){
        if (!parametroNecesidadService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un parametro-necesidad con esa id"), HttpStatus.NOT_FOUND);
        ParametroNecesidad parametroNecesidad = parametroNecesidadService.getOne(id).get();
        parametroNecesidad.setDescripcion(parametroDto.getDescripcion());
        parametroNecesidadService.save(parametroNecesidad);
        return new ResponseEntity<Message>(new Message("Parametro-necesidad actualizado"), HttpStatus.OK);
    }

    @PutMapping("/update/selected/{id}")
    public ResponseEntity<Message> updateSelected(@PathVariable("id") int id){
        if (!parametroNecesidadService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un parametro-necesidad con esa id"), HttpStatus.NOT_FOUND);
        ParametroNecesidad parametroNecesidadActivo = parametroNecesidadService.getByParametro((byte) 1).orElse(null);
        if (parametroNecesidadActivo != null){
            parametroNecesidadActivo.setParametro((byte) 0);
            parametroNecesidadService.save(parametroNecesidadActivo);
        }
        ParametroNecesidad parametroNecesidad = parametroNecesidadService.getOne(id).get();
        parametroNecesidad.setParametro((byte) 1);
        parametroNecesidadService.save(parametroNecesidad);
        return new ResponseEntity<Message>(new Message("Parametro-necesidad actualizado"), HttpStatus.OK);
    }

    @PutMapping("/delete/selected")
    public ResponseEntity<Message> deleteSelected() {
        ParametroNecesidad parametroNecesidadActivo = parametroNecesidadService.getByParametro((byte) 1).orElse(null);
        if  (parametroNecesidadActivo == null)
            return new ResponseEntity<Message>(new Message("No hay ningun otro parametro activo"), HttpStatus.NOT_FOUND);
        parametroNecesidadActivo.setParametro((byte) 0);
        parametroNecesidadService.save(parametroNecesidadActivo);
        ;
        return new ResponseEntity<Message>(new Message("Parametro-necesidad actualizado"), HttpStatus.OK);
    }
}

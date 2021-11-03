package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroAcuerdo;
import com.solicitud.solicitud.service.ParametroAcuerdoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parametro/acuerdo")
@CrossOrigin
public class ParametroAcuerdoController {

    @Autowired
    ParametroAcuerdoService parametroAcuerdoService;

    @GetMapping("/acuerdos")
    public ResponseEntity<List<ParametroAcuerdo>> list(){
        List<ParametroAcuerdo> list = parametroAcuerdoService.getAcuerdo();
        if (list.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.OK);
        return new ResponseEntity<List<ParametroAcuerdo>>(list, HttpStatus.OK);
    }

    @GetMapping("/acuerdos/selected")
    public ResponseEntity<ParametroAcuerdo> getByParametro(){
        ParametroAcuerdo parametroAcuerdo = parametroAcuerdoService.getByParametro((byte) 1).orElse(null);
        if (parametroAcuerdo == null){
            parametroAcuerdo = new ParametroAcuerdo("",(byte) 0);
            return  new ResponseEntity<ParametroAcuerdo>(parametroAcuerdo, HttpStatus.OK);
        }
        return new ResponseEntity<ParametroAcuerdo>(parametroAcuerdo, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!parametroAcuerdoService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un parametro-acuerdo con esa id"), HttpStatus.NOT_FOUND);
        ParametroAcuerdo parametroAcuerdo = parametroAcuerdoService.getOne(id).orElse(null);
        return new ResponseEntity<ParametroAcuerdo>(parametroAcuerdo, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable("id") int id){
        if(!parametroAcuerdoService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un parametro-acuerdo con esa id"), HttpStatus.NOT_FOUND);
        parametroAcuerdoService.delete(id);
        return new ResponseEntity<Message>(new Message("parametro-acuerdo eliminado"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ParametroDto parametroDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Message>(new Message("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        ParametroAcuerdo parametroAcuerdo = new ParametroAcuerdo(parametroDto.getDescripcion(), (byte) 0);
        if (parametroAcuerdoService.getAcuerdo().isEmpty())
                parametroAcuerdo.setParametro((byte) 1);
        parametroAcuerdoService.save(parametroAcuerdo);
        return new ResponseEntity<Message>(new Message("Parametro-acuerdo guardado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") int id, @RequestBody ParametroDto parametroDto){
        if (!parametroAcuerdoService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un parametro-acuerdo con esa id"), HttpStatus.NOT_FOUND);
        ParametroAcuerdo parametroAcuerdo = parametroAcuerdoService.getOne(id).get();
        parametroAcuerdo.setDescripcion(parametroDto.getDescripcion());
        parametroAcuerdoService.save(parametroAcuerdo);
        return new ResponseEntity<Message>(new Message("Parametro-acuerdo actualizado"), HttpStatus.OK);
    }

    @PutMapping("/update/selected/{id}")
    public ResponseEntity<Message> updateSelected(@PathVariable("id") int id){
        if (!parametroAcuerdoService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un parametro-acuerdo con esa id"), HttpStatus.NOT_FOUND);
        ParametroAcuerdo parametroAcuerdoActivo = parametroAcuerdoService.getByParametro((byte) 1).orElse(null);
        if (parametroAcuerdoActivo != null) {
            parametroAcuerdoActivo.setParametro((byte) 0);
            parametroAcuerdoService.save(parametroAcuerdoActivo);
        }
        ParametroAcuerdo parametroAcuerdo = parametroAcuerdoService.getOne(id).get();
        parametroAcuerdo.setParametro((byte) 1);
        parametroAcuerdoService.save(parametroAcuerdo);
        return new ResponseEntity<Message>(new Message("Parametro-acuerdo actualizado"), HttpStatus.OK);
    }

    @PutMapping("/delete/selected")
    public ResponseEntity<Message> deleteSelected() {
        ParametroAcuerdo parametroAcuerdoActivo = parametroAcuerdoService.getByParametro((byte) 1).orElse(null);
        if  (parametroAcuerdoActivo == null)
            return new ResponseEntity<Message>(new Message("No hay ningun otro parametro activo"), HttpStatus.NOT_FOUND);
        parametroAcuerdoActivo.setParametro((byte) 0);
        parametroAcuerdoService.save(parametroAcuerdoActivo);
        ;
        return new ResponseEntity<Message>(new Message("Parametro-acuerdo actualizado"), HttpStatus.OK);
    }
}

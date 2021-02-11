package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroAcuerdo;
import com.solicitud.solicitud.entity.ParametroArgumento;
import com.solicitud.solicitud.service.ParametroAcuerdoService;
import com.solicitud.solicitud.service.ParametroArgumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parametro/argumento")
@CrossOrigin
public class ParametroArgumentoController {

    @Autowired
    ParametroArgumentoService parametroArgumentoService;

    @GetMapping("/argumentos")
    public ResponseEntity<List<ParametroArgumento>> list(){
        List<ParametroArgumento> list = parametroArgumentoService.getArgumento();
        return new ResponseEntity<List<ParametroArgumento>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!parametroArgumentoService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un parametro-argumento con esa id"), HttpStatus.NOT_FOUND);
        ParametroArgumento parametroArgumento = parametroArgumentoService.getOne(id).get();
        return new ResponseEntity<ParametroArgumento>(parametroArgumento, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete (@PathVariable("id") int id){
        if(!parametroArgumentoService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un parametro-argumento con esa id"), HttpStatus.NOT_FOUND);
        parametroArgumentoService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("parametro-argumento eliminado"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ParametroDto parametroDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Mensaje>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        ParametroArgumento parametroArgumento = new ParametroArgumento(parametroDto.getDescripcion());
        parametroArgumentoService.save(parametroArgumento);
        return new ResponseEntity<Mensaje>(new Mensaje("Parametro-argumento guardado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody ParametroDto parametroDto){
        if (!parametroArgumentoService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un parametro-argumento con esa id"), HttpStatus.NOT_FOUND);
        ParametroArgumento parametroArgumento = parametroArgumentoService.getOne(id).get();
        parametroArgumento.setDescripcion(parametroDto.getDescripcion());
        parametroArgumentoService.save(parametroArgumento);
        return new ResponseEntity<Mensaje>(new Mensaje("Parametro-argumento actualizado"), HttpStatus.OK);
    }

}

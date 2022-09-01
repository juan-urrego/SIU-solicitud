package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroArgumento;
import com.solicitud.solicitud.service.ParametroArgumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parametro/argumento")
@CrossOrigin
public class ParametroArgumentoController {

    final
    ParametroArgumentoService parametroArgumentoService;

    @Autowired
    public ParametroArgumentoController(ParametroArgumentoService parametroArgumentoService) {
        this.parametroArgumentoService = parametroArgumentoService;
    }

    @GetMapping("/argumentos")
    public ResponseEntity<List<ParametroArgumento>> list(){
        List<ParametroArgumento> list = parametroArgumentoService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParametroArgumento> getById(@PathVariable(value = "id") int id){
        ParametroArgumento parametroArgumento = parametroArgumentoService.getParametroArgumentoById(id);
        return new ResponseEntity<>(parametroArgumento, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable(value = "id") int id){
        parametroArgumentoService.delete(id);
        return new ResponseEntity<>(new Message("argument parameter deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody ParametroDto parametroDto){
        parametroArgumentoService.saveParametroArgumento(parametroDto);
        return new ResponseEntity<>(new Message("argument parameter created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody ParametroDto parametroDto){
        parametroArgumentoService.update(id, parametroDto);
        return new ResponseEntity<>(new Message("argument parameter updated"), HttpStatus.OK);
    }

}

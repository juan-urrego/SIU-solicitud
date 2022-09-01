package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.InvestigadorDto;
import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.service.InvestigadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investigador")
@CrossOrigin
public class InvestigadorController {


    final
    InvestigadorService investigadorService;

    @Autowired
    public InvestigadorController(InvestigadorService investigadorService) {
        this.investigadorService = investigadorService;
    }


    @GetMapping("/investigadores")
    public ResponseEntity<List<Investigador>> list(){
        List<Investigador> list = investigadorService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investigador> getById(@PathVariable(value = "id") int id){
        Investigador investigador = investigadorService.getInvestigadorById(id);
        return new ResponseEntity<>(investigador, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable(value = "id") int id){
        investigadorService.delete(id);
        return new ResponseEntity<>(new Message("investigator deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody InvestigadorDto investigadorDto) {
        investigadorService.saveInvestigador(investigadorDto);
        return new ResponseEntity<>(new Message("investigator created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody InvestigadorDto investigadorDto) {
        investigadorService.update(id, investigadorDto);
        return new ResponseEntity<>(new Message("investigator updated"), HttpStatus.OK);
    }
}
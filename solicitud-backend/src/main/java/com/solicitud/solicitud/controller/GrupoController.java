package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.GrupoDto;
import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.entity.Grupo;
import com.solicitud.solicitud.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/grupo")
@CrossOrigin
public class GrupoController {

    final
    GrupoService grupoService;

    @Autowired
    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @GetMapping("/grupos")
    public ResponseEntity<List<Grupo>> getAll(){
        List<Grupo> list = grupoService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Grupo> getById(@PathVariable(value = "id") int id){
        Grupo grupo = grupoService.getGrupoById(id);
        return new ResponseEntity<>(grupo, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Message> delete (@PathVariable(value = "id") int id){
        grupoService.delete(id);
        return new ResponseEntity<>(new Message("group deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Message> save(@RequestBody GrupoDto grupoDto){
        grupoService.saveGrupo(grupoDto);
        return new ResponseEntity<>(new Message("group created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody GrupoDto grupoDto){
        grupoService.update(id, grupoDto);
        return new ResponseEntity<>(new Message("group updated"), HttpStatus.OK);
    }
}
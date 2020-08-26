package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.entity.Grupo;
import com.solicitud.solicitud.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GrupoController {

    @Autowired
    private GrupoService service;

    @PostMapping("/grupos/agregar")
    public Grupo addGrupo(@RequestBody Grupo grupo){
        return  service.saveGrupo(grupo);
    }

    @GetMapping("/grupos")
    public ResponseEntity<List<Grupo>> list(){
        List<Grupo> list = service.getGrupos();
        return new ResponseEntity<List<Grupo>>(list, HttpStatus.OK);
    }

    @GetMapping("/grupos/{id}")
    public ResponseEntity<Grupo> grupoById(@PathVariable("id") int id){
        Grupo grupo = service.getGrupoById(id);
        return new ResponseEntity<Grupo>(grupo,HttpStatus.OK);
    }

    @PutMapping("/grupos/actualizar/{id}")
    public Grupo updateGrupo(@RequestBody Grupo grupo, @PathVariable int id){
        return service.updateGrupo(grupo,id);
    }

    @DeleteMapping("/grupos/eliminar/{id}")
    public String deleteGrupo (@PathVariable int id){
        return service.deleteGrupo(id);
    }
}

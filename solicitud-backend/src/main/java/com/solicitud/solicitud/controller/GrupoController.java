package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.entity.Grupo;
import com.solicitud.solicitud.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GrupoController {

    @Autowired
    private GrupoService service;

    @PostMapping("/grupos/agregar")
    public Grupo addGrupo(@RequestBody Grupo grupo){
        return  service.saveGrupo(grupo);
    }

    @GetMapping("/grupos")
    public List<Grupo> findAllGrupo(){
        return  service.getGrupos();
    }

    @GetMapping("/grupos/{id}")
    public Grupo findGrupoById(@PathVariable int id){
        return service.getGrupoById(id);
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

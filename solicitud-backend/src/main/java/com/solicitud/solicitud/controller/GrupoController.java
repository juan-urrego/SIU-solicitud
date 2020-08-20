package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.entity.Grupo;
import com.solicitud.solicitud.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GrupoController {

    @Autowired
    private GrupoService service;

    @PostMapping("/grupos/agregar")
    public Grupo addGrupo(@RequestBody Grupo grupo){
        return  service.saveGrupo(grupo);
    }

    @PreAuthorize("denyAll()")
    @GetMapping("/grupos")
//    public List<Grupo> findAllGrupo(){
//        return  service.getGrupos();
//    }

    public ResponseEntity<Grupo> list(){
        List<Grupo> list = service.getGrupos();
        return new ResponseEntity(list, HttpStatus.OK);
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

package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.entity.Director;
import com.solicitud.solicitud.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DirectorController {

    @Autowired
    private DirectorService service;

    @PostMapping("/directores/agregar")
    public Director addDirector(@RequestBody Director director){
        return  service.saveDirector(director);
    }

    @GetMapping("/directores")
    public List<Director> findAllDirector(){
        return  service.getDirectors();
    }

    @GetMapping("/directores/{id}")
    public Director findDirectorById(@PathVariable int id){
        return service.getDirectorById(id);
    }

    @PutMapping("/directores/actualizar/{id}")
    public Director updateDirector(@RequestBody Director director, @PathVariable int id){
        return service.updateDirector(director,id);
    }

    @DeleteMapping("/directores/eliminar/{id}")
    public String deleteDirector (@PathVariable int id){
        return service.deleteDirector(id);
    }
}
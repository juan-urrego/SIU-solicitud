package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EstudioController {

    @Autowired
    private EstudioService service;

    @PostMapping("/estudios/agregar")
    public Estudio addEstudio(@RequestBody Estudio estudio){
        return  service.saveEstudio(estudio);
    }

    @GetMapping("/estudios")
    public List<Estudio> findAllEstudio(){
        return  service.getEstudios();
    }

    @GetMapping("/estudios/{id}")
    public Estudio findEstudioById(@PathVariable int id){
        return service.getEstudioById(id);
    }

    @PutMapping("/estudios/actualizar/{id}")
    public Estudio updateEstudio(@RequestBody Estudio estudio, @PathVariable int id){
        return service.updateEstudio(estudio,id);
    }

    @DeleteMapping("/estudios/eliminar/{id}")
    public String deleteEstudio (@PathVariable int id){
        return service.deleteEstudio(id);
    }
}
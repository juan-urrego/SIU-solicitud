package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.entity.Precotizacion;
import com.solicitud.solicitud.service.PrecotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PrecotizacionController {

    @Autowired
    PrecotizacionService service;

    @PostMapping("/precotizaciones/agregar")
    public Precotizacion addPrecotizacion(@RequestBody Precotizacion precotizacion){
        return  service.savePrecotizacion(precotizacion);
    }

    @GetMapping("/precotizaciones")
    public List<Precotizacion> findAllPrecotizacion(){
        return  service.getPrecotizaciones();
    }

    @GetMapping("/precotizaciones/{id}")
    public Precotizacion findPrecotizacionById(@PathVariable int id){
        return service.getPrecotizacionById(id);
    }

    @PutMapping("/precotizaciones/actualizar/{id}")
    public Precotizacion updatePrecotizacion(@RequestBody Precotizacion precotizacion,@PathVariable int id){
        return service.updatePrecotizacion(precotizacion,id);
    }

    @DeleteMapping("/precotizaciones/eliminar/{id}")
    public String deletePrecotizacion (@PathVariable int id){
        return service.deletePrecotizacion(id);
    }

}

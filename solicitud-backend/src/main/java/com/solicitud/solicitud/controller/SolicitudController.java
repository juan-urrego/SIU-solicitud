package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.entity.Solicitud;
import com.solicitud.solicitud.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SolicitudController {

    @Autowired
    SolicitudService service;

    @PostMapping("/solicitudes/agregar")
    public Solicitud addSolicitud(@RequestBody Solicitud solicitud){
        return  service.saveSolicitud(solicitud);
    }

    @GetMapping("/solicitudes")
    public List<Solicitud> findAllSolicitud(){
        return  service.getSolicitudes();
    }

    @GetMapping("/solicitudes/{id}")
    public Solicitud findSolicitudById(@PathVariable int id){
        return service.getSolicitudById(id);
    }

    @PostMapping("/solicitudes/documents/{id}")
    public String createDocuments(@PathVariable int id){
        return service.createDocuments(id);
    }

    @PutMapping("/solicitudes/actualizar/{id}")
    public Solicitud updateSolicitud(@RequestBody Solicitud solicitud, @PathVariable int id){
        return service.updateSolicitud(solicitud, id);
    }

    @DeleteMapping("/solicitudes/eliminar/{id}")
    public String deleteSolicitud (@PathVariable int id){
        return service.deleteSolicitud(id);
    }

}

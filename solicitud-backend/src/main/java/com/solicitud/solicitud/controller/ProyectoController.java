package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.InvestigadorDto;
import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.dto.ProyectoDto;
import com.solicitud.solicitud.entity.Grupo;
import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.entity.Proyecto;
import com.solicitud.solicitud.service.GrupoService;
import com.solicitud.solicitud.service.InvestigadorService;
import com.solicitud.solicitud.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    GrupoService grupoService;

    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = proyectoService.getProyecto();
        return new ResponseEntity<List<Proyecto>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!proyectoService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un proyecto con esa id"), HttpStatus.NOT_FOUND);
        Proyecto proyecto = proyectoService.getOne(id).get();
        return new ResponseEntity<Proyecto>(proyecto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete (@PathVariable("id") int id){
        if(!proyectoService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un Proyecto con esa id"), HttpStatus.NOT_FOUND);
        proyectoService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProyectoDto proyectoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Mensaje>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        Grupo grupo = grupoService.getOne(proyectoDto.getGrupo()).get();
        Proyecto proyecto = new Proyecto(proyectoDto.getNombre(), proyectoDto.getCodigoProyecto(), proyectoDto.getCentroCostos(), grupo);
        proyectoService.save(proyecto);
        return new ResponseEntity<Mensaje>(new Mensaje("Proyecto guardado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody ProyectoDto proyectoDto){
        if (!proyectoService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un investigador con esa id"), HttpStatus.NOT_FOUND);
        Grupo grupo = grupoService.getOne(proyectoDto.getGrupo()).get();
        Proyecto proyecto = proyectoService.getOne(id).get();
        proyecto.setNombre(proyectoDto.getNombre());
        proyecto.setCodigoProyecto(proyectoDto.getCodigoProyecto());
        proyecto.setCentroCostos(proyectoDto.getCentroCostos());
        proyecto.setGrupo(grupo);
        proyectoService.save(proyecto);
        return new ResponseEntity<Mensaje>(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }
}

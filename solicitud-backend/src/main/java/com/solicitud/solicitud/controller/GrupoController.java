package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.GrupoDto;
import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.entity.Grupo;
import com.solicitud.solicitud.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/grupo")
@CrossOrigin
public class GrupoController {


    @Autowired
    GrupoService grupoService;

    @GetMapping("/grupos")
    public ResponseEntity<List<Grupo>> list(){
        List<Grupo> list = grupoService.getGrupo();
        return new ResponseEntity<List<Grupo>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!grupoService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un grupo con esa id"), HttpStatus.NOT_FOUND);
        Grupo grupo = grupoService.getOne(id).get();
        return new ResponseEntity<Grupo>(grupo, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Message> delete (@PathVariable("id") int id){
        if(!grupoService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un grupo con esa id"), HttpStatus.NOT_FOUND);
        grupoService.delete(id);
        return new ResponseEntity<Message>(new Message("Grupo eliminado"), HttpStatus.OK);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@RequestBody GrupoDto grupoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Message>(new Message("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        Grupo grupo = new Grupo(grupoDto.getCodigoGrupo(), grupoDto.getNombre(), grupoDto.getCodColciencia());
        grupoService.save(grupo);
        return new ResponseEntity<Message>(new Message("Grupo guardado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Message> update(@PathVariable("id") int id, @RequestBody GrupoDto grupoDto){
        if (!grupoService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un grupo con esa id"), HttpStatus.NOT_FOUND);
        Grupo grupo = grupoService.getOne(id).get();
        grupo.setCodigoGrupo(grupoDto.getCodigoGrupo());
        grupo.setNombre(grupoDto.getNombre());
        grupo.setCodColciencia(grupoDto.getCodColciencia());
        grupoService.save(grupo);
        return new ResponseEntity<Message>(new Message("Grupo actualizado"), HttpStatus.OK);
    }
}
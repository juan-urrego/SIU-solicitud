package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.EstudioDto;
import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.entity.*;
import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.enums.EstadoNombre;
import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.service.UserService;
import com.solicitud.solicitud.service.*;
import com.solicitud.solicitud.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudio")
@CrossOrigin
public class EstudioController {


    @Autowired
    EstudioService estudioService;


    @Autowired
    EstadoService estadoService;

    @Autowired
    UnidadAcademicaService unidadAcademicaService;

    @Autowired
    UserService userService;



    @GetMapping("/estudios")
    public ResponseEntity<List<Estudio>> list(){
        List<Estudio> list = estudioService.getEstudios();
        return new ResponseEntity<List<Estudio>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!estudioService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un estudio con esa id"), HttpStatus.NOT_FOUND);
        if(!estudioService.getOne(id).isPresent())
            return new ResponseEntity<Message>(new Message("No tiene acceso a este estudio"), HttpStatus.FORBIDDEN);
        Estudio estudio = estudioService.getOne(id).get();
        return new ResponseEntity<Estudio>(estudio, HttpStatus.OK);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") int id, @RequestBody EstudioDto estudioDto){
        if (!estudioService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un estudio con esa id"), HttpStatus.NOT_FOUND);
        Estudio estudio = estudioService.getOne(id).get();
        estudio.setUnidadAcademica(estudioDto.getUnidadAcademica());
        if (estudioDto.isFirmaUsuario()){
            Estado estado = estadoService.getByEstadoNombre(EstadoNombre.USUARIO_FIRMADO).get();
            estudio.setFirmaUsuario(estudio.getSolicitud().getUser().getSignatureUrl());
            estudio.setEstado(estado);
        }
        if (estudioDto.isFirmaDirector()){
            Estado estado = estadoService.getByEstadoNombre(EstadoNombre.DIRECTOR_FIRMADO).get();
            estudio.setFirmaDirector(estudio.getDirector().getSignatureUrl());
            estudio.setEstado(estado);
        }
        if (estudio.getUnidadAcademica() != null &&
                estudio.getFirmaDirector() != null &&
                estudio.getFirmaUsuario() != null){
            Estado estado = estadoService.getByEstadoNombre(EstadoNombre.FIRMADO).get();
            estudio.setEstado(estado);
        }
        estudioService.save(estudio);
        return new ResponseEntity<Message>(new Message("Estudio actualizado"), HttpStatus.OK);
    }

    @PostMapping("/confirmar/{id}")
    public ResponseEntity<Message> confirmar(@PathVariable int id){
        if (!estudioService.existsById(id))
            return new ResponseEntity<Message>(new Message("No existe un estudio con esa id"), HttpStatus.NOT_FOUND);
        Estudio estudio = estudioService.getOne(id).get();
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.VERIFICADA).get();
        if (estudio.getEstado().getEstadoNombre().equals(EstadoNombre.FIRMADO)){
            estudio.setEstado(estado);
        } else {
            return new ResponseEntity<Message>(new Message("Documento aún no firmado"), HttpStatus.BAD_REQUEST);
        }
        estudioService.save(estudio);
        return new ResponseEntity<Message>(new Message("Estudio verificado correctamente"), HttpStatus.OK);
    }
}
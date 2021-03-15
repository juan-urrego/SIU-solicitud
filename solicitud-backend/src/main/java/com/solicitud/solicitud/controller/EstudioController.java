package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.EstudioDto;
import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.entity.*;
import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.enums.EstadoNombre;
import com.solicitud.solicitud.service.*;
import com.solicitud.solicitud.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



    @GetMapping("/estudios")
    public ResponseEntity<List<Estudio>> list(){
        List<Estudio> list = estudioService.getEstudio();
        return new ResponseEntity<List<Estudio>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!estudioService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un estudio con esa id"), HttpStatus.NOT_FOUND);
        Estudio estudio = estudioService.getOne(id).get();
        return new ResponseEntity<Estudio>(estudio, HttpStatus.OK);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody EstudioDto estudioDto){
        if (!estudioService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un estudio con esa id"), HttpStatus.NOT_FOUND);
        Estudio estudio = estudioService.getOne(id).get();
        UnidadAcademica unidadAcademica = unidadAcademicaService.getOne(estudioDto.getUnidadAcademica()).get();
        estudio.setUnidadAcademica(unidadAcademica);
        estudio.setAcuerdo(estudioDto.getAcuerdo());
        estudio.setFirmaInvestigador(estudioDto.getFirmaInvestigador());
        estudio.setFirmaUsuario(estudioDto.getFirmaUsuario());
        estudioService.save(estudio);
        return new ResponseEntity<Mensaje>(new Mensaje("Estudio actualizado"), HttpStatus.OK);
    }

    @PostMapping("/confirmar/{id}")
    public ResponseEntity<Mensaje> confirmar(@PathVariable int id){
        if (!estudioService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un estudio con esa id"), HttpStatus.NOT_FOUND);
        Estudio estudio = estudioService.getOne(id).get();
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.VERIFICADA).get();
        if ((estudio.getFirmaInvestigador() == (byte) 1) && (estudio.getFirmaUsuario() == (byte) 1)){
            estudio.setEstado(estado);
        } else {
            return new ResponseEntity<Mensaje>(new Mensaje("Documento aún no firmado"), HttpStatus.BAD_REQUEST);
        }
        estudioService.save(estudio);
        return new ResponseEntity<Mensaje>(new Mensaje("Estudio verificado correctamente"), HttpStatus.OK);
    }
}
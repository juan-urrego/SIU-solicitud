package com.solicitud.solicitud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solicitud.solicitud.dto.InvestigadorDto;
import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.service.InvestigadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/investigador")
@CrossOrigin
public class InvestigadorController {


    @Autowired
    InvestigadorService investigadorService;


    @GetMapping("/investigadores")
    public ResponseEntity<List<Investigador>> list(){
        List<Investigador> list = investigadorService.getInvestigador();
        return new ResponseEntity<List<Investigador>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!investigadorService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un investigador con esa id"), HttpStatus.NOT_FOUND);
        Investigador investigador = investigadorService.getOne(id).get();
        return new ResponseEntity<Investigador>(investigador, HttpStatus.OK);
    }

    @GetMapping(value= "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImageById(@PathVariable("id") int id){
        if (!investigadorService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un investigador con esa id"), HttpStatus.NOT_FOUND);
        FileSystemResource file = investigadorService.findImageById(id);
        return new ResponseEntity<FileSystemResource>(file, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete (@PathVariable("id") int id){
        if(!investigadorService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un investigador con esa id"), HttpStatus.NOT_FOUND);
        investigadorService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("Investigador eliminado"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam(value = "investigador") String model, @RequestParam(value = "imageFile") MultipartFile file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InvestigadorDto investigadorDto = mapper.readValue(model, InvestigadorDto.class);
        if(investigadorService.existByIdentificacion(investigadorDto.getIdentificacion()))
            return new ResponseEntity<Mensaje>(new Mensaje("Ya hay un investigador con esa identificacion"), HttpStatus.BAD_REQUEST);
        Investigador investigador = new Investigador(investigadorDto.getIdentificacion(), investigadorDto.getNombre(), investigadorDto.getTelefono(), investigadorDto.getEmail(), investigadorService.saveImage(file.getBytes(), file.getOriginalFilename()));
        investigadorService.save(investigador);
        return new ResponseEntity<Mensaje>(new Mensaje("Investigador guardado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestParam(value = "investigador") String model, @RequestParam("imageFile") MultipartFile file) throws Exception  {
        ObjectMapper mapper = new ObjectMapper();
        InvestigadorDto investigadorDto = mapper.readValue(model, InvestigadorDto.class);
        if (!investigadorService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un investigador con esa id"), HttpStatus.NOT_FOUND);
        Investigador investigador = investigadorService.getOne(id).get();
        investigador.setNombre(investigadorDto.getNombre());
        investigador.setTelefono(investigadorDto.getTelefono());
        investigador.setEmail(investigadorDto.getEmail());
        investigador.setFirma(investigadorService.saveImage(file.getBytes(), file.getOriginalFilename()));
        investigadorService.save(investigador);
        return new ResponseEntity<Mensaje>(new Mensaje("Investigador actualizado"), HttpStatus.OK);
    }
}
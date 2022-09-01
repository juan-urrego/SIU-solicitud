package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.EstudioDto;
import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.service.EstudioService;
import com.solicitud.solicitud.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/estudio")
@CrossOrigin
public class EstudioController {

    final
    EstudioService estudioService;

    final ReportService reportService;

    @Autowired
    public EstudioController(EstudioService estudioService, ReportService reportService) {
        this.estudioService = estudioService;
        this.reportService = reportService;
    }

    @GetMapping("/estudios")
    public ResponseEntity<List<Estudio>> list(){
        List<Estudio> list = estudioService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/estudios/{id}")
    public ResponseEntity<?> pdf(@PathVariable("id")int id) throws JRException, FileNotFoundException {
        String resultado = reportService.exportEstudio(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") int id){
        Estudio estudio = estudioService.getEstudioById(id);
        return new ResponseEntity<>(estudio, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody EstudioDto estudioDto){
        estudioService.update(id, estudioDto);
        return new ResponseEntity<>(new Message("modified"), HttpStatus.OK);
    }
}
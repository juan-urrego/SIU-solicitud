package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.dto.Mail;
import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.dto.SolicitudDto;
import com.solicitud.solicitud.entity.Solicitud;
import com.solicitud.solicitud.service.ReportService;
import com.solicitud.solicitud.service.SolicitudService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/solicitud")
@CrossOrigin
public class SolicitudController {


    final
    SolicitudService solicitudService;

    final ReportService reportService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService, ReportService reportService) {
        this.solicitudService = solicitudService;
        this.reportService = reportService;
    }


    @GetMapping("/solicitudes")
    public ResponseEntity<?> list(){
        List<Solicitud> list = solicitudService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/solicitudes/{id}")
    public ResponseEntity<?> pdf(@PathVariable("id")int id) throws JRException, FileNotFoundException {
        String resultado = reportService.exportReport(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getById(@PathVariable("id") int id){
        Solicitud solicitud = solicitudService.getSolicitudById(id);
        return new ResponseEntity<>(solicitud, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete (@PathVariable("id") int id){
        solicitudService.delete(id);
        return new ResponseEntity<>(new Message("document deleted"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody SolicitudDto solicitudDto){
        solicitudService.saveSolicitud(solicitudDto);
        return new ResponseEntity<>(new Message("document created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable(value = "id") int id, @RequestBody SolicitudDto solicitudDto){
        solicitudService.updated(id, solicitudDto);
        return new ResponseEntity<>(new Message("document updated"), HttpStatus.OK);
    }

    @PutMapping("/confirmar/{id}")
    public ResponseEntity<Message> confirmar(@PathVariable(value = "id") int id){
        solicitudService.verifySolicitud(id);
        return new ResponseEntity<>(new Message("document verified"), HttpStatus.OK);
    }

    @PostMapping("/crear/{id}")
    public ResponseEntity<Message> createDocuments(@PathVariable(value = "id") int id){
        solicitudService.createDocuments(id);
        return new ResponseEntity<>(new Message("documents created"), HttpStatus.OK);
    }

    @PutMapping("/email/{id}")
    public ResponseEntity<?> sendEmail(@PathVariable(value = "id") int id, @RequestBody Mail mail){
        solicitudService.sendMail(id, mail);
        return new ResponseEntity<>(new Message("mail sent"), HttpStatus.OK);
    }
}
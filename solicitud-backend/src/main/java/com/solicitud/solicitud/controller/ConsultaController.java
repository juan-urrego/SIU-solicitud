package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.Message;
import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.service.ConsultaService;
import com.solicitud.solicitud.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/consulta")
@CrossOrigin
public class ConsultaController {

    final
    ConsultaService consultaService;

    final ReportService reportService;

    @Autowired
    public ConsultaController(ConsultaService consultaService, ReportService reportService) {
        this.consultaService = consultaService;
        this.reportService = reportService;
    }

    @GetMapping("/consultas")
    public ResponseEntity<List<Consulta>> list(){
        List<Consulta> list = consultaService.getll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/consultas/{id}")
    public ResponseEntity<?> pdf(@PathVariable("id")int id) throws JRException, FileNotFoundException {
        String resultado = reportService.exportConsulta(id);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") int id){
        Consulta consulta = consultaService.getConsultaById(id);
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    }


    @PutMapping("/confirmar/{id}")
    public ResponseEntity<Message> confirmar(@PathVariable int id){
        consultaService.confirmarConsulta(id);
        return new ResponseEntity<>(new Message("verified"), HttpStatus.OK);
    }
}
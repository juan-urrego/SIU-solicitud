package com.solicitud.solicitud.controller;

import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.dto.ParametroDto;
import com.solicitud.solicitud.entity.ParametroAcuerdo;
import com.solicitud.solicitud.entity.ParametroConsulta;
import com.solicitud.solicitud.service.ParametroAcuerdoService;
import com.solicitud.solicitud.service.ParametroConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parametro/consulta")
@CrossOrigin
public class ParametroConsultaController {

    @Autowired
    ParametroConsultaService parametroConsultaService;

    @GetMapping("/consultas")
    public ResponseEntity<List<ParametroConsulta>> list(){
        List<ParametroConsulta> list = parametroConsultaService.getConsulta();
        if (list.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.OK);
        return new ResponseEntity<List<ParametroConsulta>>(list, HttpStatus.OK);
    }

    @GetMapping("/consultas/selected")
    public ResponseEntity<ParametroConsulta> getByParametro(){
        ParametroConsulta parametroConsulta = parametroConsultaService.getByParametro((byte) 1).orElse(null);
        if (parametroConsulta == null){
            parametroConsulta = new ParametroConsulta("",(byte) 0);
            return  new ResponseEntity<ParametroConsulta>(parametroConsulta, HttpStatus.OK);
        }
        return new ResponseEntity<ParametroConsulta>(parametroConsulta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!parametroConsultaService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un parametro-consulta con esa id"), HttpStatus.NOT_FOUND);
        ParametroConsulta parametroConsulta = parametroConsultaService.getOne(id).get();
        return new ResponseEntity<ParametroConsulta>(parametroConsulta, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete (@PathVariable("id") int id){
        if(!parametroConsultaService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un parametro-consulta con esa id"), HttpStatus.NOT_FOUND);
        parametroConsultaService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("parametro-consulta eliminado"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ParametroDto parametroDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Mensaje>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        ParametroConsulta parametroConsulta= new ParametroConsulta(parametroDto.getDescripcion(), (byte) 0);
        if (parametroConsultaService.getConsulta().isEmpty())
            parametroConsulta.setParametro((byte) 1);
        parametroConsultaService.save(parametroConsulta);
        return new ResponseEntity<Mensaje>(new Mensaje("Parametro-consulta guardado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody ParametroDto parametroDto){
        if (!parametroConsultaService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un parametro-consulta con esa id"), HttpStatus.NOT_FOUND);
        ParametroConsulta parametroConsulta = parametroConsultaService.getOne(id).get();
        parametroConsulta.setDescripcion(parametroDto.getDescripcion());
        parametroConsultaService.save(parametroConsulta);
        return new ResponseEntity<Mensaje>(new Mensaje("Parametro-consulta actualizado"), HttpStatus.OK);
    }

    @PutMapping("/update/selected/{id}")
    public ResponseEntity<Mensaje> updateSelected(@PathVariable("id") int id){
        if (!parametroConsultaService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un parametro-consulta con esa id"), HttpStatus.NOT_FOUND);
        ParametroConsulta parametroConsultaActivo = parametroConsultaService.getByParametro((byte) 1).orElse(null);
        if (parametroConsultaActivo != null) {
            parametroConsultaActivo.setParametro((byte) 0);
            parametroConsultaService.save(parametroConsultaActivo);
        }
        ParametroConsulta parametroConsulta = parametroConsultaService.getOne(id).get();
        parametroConsulta.setParametro((byte) 1);
        parametroConsultaService.save(parametroConsulta);
        return new ResponseEntity<Mensaje>(new Mensaje("Parametro-consulta actualizado"), HttpStatus.OK);
    }

    @PutMapping("/delete/selected")
    public ResponseEntity<Mensaje> deleteSelected() {
        ParametroConsulta parametroConsultaActivo = parametroConsultaService.getByParametro((byte) 1).orElse(null);
        if  (parametroConsultaActivo == null)
            return new ResponseEntity<Mensaje>(new Mensaje("No hay ningun otro parametro activo"), HttpStatus.NOT_FOUND);
        parametroConsultaActivo.setParametro((byte) 0);
        parametroConsultaService.save(parametroConsultaActivo);
        ;
        return new ResponseEntity<Mensaje>(new Mensaje("Parametro-consulta actualizado"), HttpStatus.OK);
    }
}

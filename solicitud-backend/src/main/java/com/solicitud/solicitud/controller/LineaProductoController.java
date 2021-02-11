package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.dto.LineaProductoDto;
import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.dto.UnidadAcademicaDto;
import com.solicitud.solicitud.entity.LineaProducto;
import com.solicitud.solicitud.entity.UnidadAcademica;
import com.solicitud.solicitud.service.LineaProductoService;
import com.solicitud.solicitud.service.UnidadAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lineaProducto")
@CrossOrigin
public class LineaProductoController {


    @Autowired
    LineaProductoService lineaProductoService;

    @GetMapping("/lineasProductos")
    public ResponseEntity<List<LineaProducto>> list(){
        List<LineaProducto> list = lineaProductoService.getLineaProducto();
        return new ResponseEntity<List<LineaProducto>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!lineaProductoService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una LineaProducto con esa id"), HttpStatus.NOT_FOUND);
        LineaProducto lineaProducto = lineaProductoService.getOne(id).get();
        return new ResponseEntity<LineaProducto>(lineaProducto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete (@PathVariable("id") int id){
        if(!lineaProductoService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una LineaProducto con esa id"), HttpStatus.NOT_FOUND);
        lineaProductoService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("LineaProducto eliminada"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody LineaProductoDto lineaProductoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Mensaje>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        LineaProducto lineaProducto = new LineaProducto(lineaProductoDto.getLineaNombre());
        lineaProductoService.save(lineaProducto);
        return new ResponseEntity<Mensaje>(new Mensaje("LineaProducto guardada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody LineaProductoDto lineaProductoDto){
        if (!lineaProductoService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un LineaProducto con esa id"), HttpStatus.NOT_FOUND);
        LineaProducto lineaProducto = lineaProductoService.getOne(id).get();
        lineaProducto.setNombre(lineaProductoDto.getLineaNombre());
        lineaProductoService.save(lineaProducto);
        return new ResponseEntity<Mensaje>(new Mensaje("LineaProducto actualizado"), HttpStatus.OK);
    }
}

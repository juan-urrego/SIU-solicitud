package com.solicitud.solicitud.controller;


import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.dto.SolicitudDto;
import com.solicitud.solicitud.entity.*;
import com.solicitud.solicitud.entity.Solicitud;
import com.solicitud.solicitud.enums.EstadoNombre;
import com.solicitud.solicitud.security.entity.Usuario;
import com.solicitud.solicitud.security.service.UsuarioService;
import com.solicitud.solicitud.service.*;
import com.solicitud.solicitud.service.SolicitudService;
import jdk.javadoc.internal.doclets.toolkit.MethodWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/solicitud")
@CrossOrigin
public class SolicitudController {


    @Autowired
    SolicitudService solicitudService;

    @Autowired
    ParametroAcuerdoService parametroAcuerdoService;

    @Autowired
    GrupoService grupoService;

    @Autowired
    InvestigadorService investigadorService;

    @Autowired
    EstadoService estadoService;

    @Autowired
    ProveedorService proveedorService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    LineaProductoService lineaProductoService;

    @Autowired
    EstudioService estudioService;

    @Autowired
    ConsultaService consultaService;

    @Autowired
    GrupoInvestigadorService grupoInvestigadorService;

    @Autowired
    ParametroConsultaService parametroConsultaService;


    @GetMapping("/solicitudes")
    public ResponseEntity<List<Solicitud>> list(){
        List<Solicitud> list = solicitudService.getSolicitud();
        return new ResponseEntity<List<Solicitud>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!solicitudService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una solicitud con esa id"), HttpStatus.NOT_FOUND);
        Solicitud solicitud = solicitudService.getOne(id).get();
        return new ResponseEntity<Solicitud>(solicitud, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete (@PathVariable("id") int id){
        if(!solicitudService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una solicitud con esa id"), HttpStatus.NOT_FOUND);
        solicitudService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("Solicitud eliminada"), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SolicitudDto solicitudDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Mensaje>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        Grupo grupo = grupoService.getOne(solicitudDto.getGrupo()).get();
        Investigador investigador = investigadorService.getOne(solicitudDto.getInvestigador()).get();
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA).get();
        GrupoInvestigador grupoInvestigador = new GrupoInvestigador(solicitudDto.getCargo(), solicitudDto.getNombreContacto(), solicitudDto.getTelefonoContacto(), grupo, investigador);
        Set<DetalleTramite> detalleTramites = new HashSet<>();
        solicitudDto.getDetalleTramiteDtos().forEach((detalleTramitesx) ->{
            DetalleTramite detalleTramite = new DetalleTramite(detalleTramitesx.getDescripcion(), detalleTramitesx.getCantidad(), detalleTramitesx.getLineaProducto());
            detalleTramites.add(detalleTramite);
        });
        Set<Precotizacion> precotizaciones = new HashSet<>();
        Precotizacion precotizacionElegida = new Precotizacion();
        solicitudDto.getPrecotizacionDtos().forEach((precotizacion) ->{
            Precotizacion precotizacion1 = new Precotizacion(precotizacion.getValorTotal(), precotizacion.getValorIva(), precotizacion.getProveedor());
            precotizaciones.add(precotizacion1);
            if(precotizacion == solicitudDto.getPrecotizacionDto()){
                Set<Argumento> argumentos = new HashSet<>();
                solicitudDto.getArgumentoDtos().forEach((argumentosx) -> {
                    Argumento argumento = new Argumento(argumentosx.getDescripcion(), argumentosx.getPrecotizacion());
                    argumentos.add(argumento);
                });
                precotizacionElegida.setValorTotal(precotizacion1.getValorTotal());
                precotizacionElegida.setValorIva(precotizacion1.getValorIva());
                precotizacionElegida.setProveedor(precotizacion1.getProveedor());
                precotizacionElegida.setArgumentos(argumentos);
            }
        });
        Solicitud solicitud = new Solicitud(solicitudDto.getTipoTramite(),
                solicitudDto.getNecesidad(),
                solicitudDto.getFecha(),
                solicitudDto.getValor(),
                solicitudDto.getVerificacion(),
                solicitudDto.getObservacion(),
                grupoInvestigador,
                estado,
                precotizaciones,
                precotizacionElegida,
                detalleTramites
                );
        solicitudService.save(solicitud);
        return new ResponseEntity<Mensaje>(new Mensaje("Solicitud guardada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody SolicitudDto solicitudDto){
        if (!solicitudService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un solicitud con esa id"), HttpStatus.NOT_FOUND);
        Grupo grupo = grupoService.getOne(solicitudDto.getGrupo()).get();
        Investigador investigador = investigadorService.getOne(solicitudDto.getInvestigador()).get();
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA).get();
        GrupoInvestigador grupoInvestigador = new GrupoInvestigador(solicitudDto.getCargo(), solicitudDto.getNombreContacto(), solicitudDto.getTelefonoContacto(), grupo, investigador);
        Set<DetalleTramite> detalleTramites = new HashSet<>();
        solicitudDto.getDetalleTramiteDtos().forEach((detalleTramitesx) ->{
            DetalleTramite detalleTramite = new DetalleTramite(detalleTramitesx.getDescripcion(), detalleTramitesx.getCantidad(), detalleTramitesx.getLineaProducto());
            detalleTramites.add(detalleTramite);
        });
        Set<Precotizacion> precotizaciones = new HashSet<>();
        Precotizacion precotizacionElegida = new Precotizacion();
        solicitudDto.getPrecotizacionDtos().forEach((precotizacion) ->{
            Precotizacion precotizacion1 = new Precotizacion(precotizacion.getValorTotal(), precotizacion.getValorIva(), precotizacion.getProveedor());
            precotizaciones.add(precotizacion1);
            if(precotizacion == solicitudDto.getPrecotizacionDto()){
                Set<Argumento> argumentos = new HashSet<>();
                solicitudDto.getArgumentoDtos().forEach((argumentosx) -> {
                    Argumento argumento = new Argumento(argumentosx.getDescripcion(), argumentosx.getPrecotizacion());
                    argumentos.add(argumento);
                });
                precotizacionElegida.setValorTotal(precotizacion1.getValorTotal());
                precotizacionElegida.setValorIva(precotizacion1.getValorIva());
                precotizacionElegida.setProveedor(precotizacion1.getProveedor());
                precotizacionElegida.setArgumentos(argumentos);
            }
        });
        Solicitud solicitud = solicitudService.getOne(id).get();
        solicitud.getPrecotizaciones().clear();
        solicitud.setTipoTramite(solicitudDto.getTipoTramite());
        solicitud.setNecesidad(solicitudDto.getNecesidad());
        solicitud.setFecha(solicitudDto.getFecha());
        solicitud.setValor(solicitudDto.getValor());
        solicitud.setVerificacion(solicitudDto.getVerificacion());
        solicitud.setObservacion(solicitudDto.getObservacion());
        solicitud.setGrupoInvestigador(grupoInvestigador);
        solicitud.setEstado(estado);
        solicitud.setPrecotizaciones(precotizaciones);
        solicitud.setPrecotizacionElegida(precotizacionElegida);
        solicitud.setDetalleTramites(detalleTramites);
        solicitudService.save(solicitud);
        return new ResponseEntity<Mensaje>(new Mensaje("Solicitud actualizada"), HttpStatus.OK);
    }

    @PostMapping("/confirmar/{id}")
    public ResponseEntity<Mensaje> confirmar(@PathVariable int id){
        if (!solicitudService.existsById(id))
            new ResponseEntity<Mensaje>(new Mensaje("No existe una solicitud con esa id"), HttpStatus.NOT_FOUND);
        Solicitud solicitud = solicitudService.getOne(id).get();
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.VERIFICADA).get();
        solicitud.setEstado(estado);
        solicitudService.save(solicitud);
        return new ResponseEntity<Mensaje>(new Mensaje("Solicitud verificada correctamente"), HttpStatus.OK);
    }

    @PostMapping("/crear/{id}/{idUser}")
    public ResponseEntity<Mensaje> createDocuments(@PathVariable int id, @PathVariable int idUser){
        if (!solicitudService.existsById(id))
            new ResponseEntity<Mensaje>(new Mensaje("No existe una solicitud con esa id"), HttpStatus.NOT_FOUND);
        Solicitud solicitud = solicitudService.getOne(id).get();
        if (solicitud.getEstado().getEstadoNombre() == EstadoNombre.CREADA)
            new ResponseEntity<Mensaje>(new Mensaje("El documento aún no está verificado para la creación de documentos"), HttpStatus.BAD_REQUEST);
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA).get();
        ParametroConsulta parametroConsulta = parametroConsultaService.getByParametro((byte) 1).get();
        ParametroAcuerdo parametroAcuerdo = parametroAcuerdoService.getByParametro((byte) 1).get();
        Usuario usuario = usuarioService.getOne(idUser).get();
        Consulta consulta = new Consulta(parametroConsulta.getDescripcion(), solicitud, estado);
        Estudio estudio = new Estudio(parametroAcuerdo.getDescripcion(), (byte) 0, (byte) 0, solicitud, null, estado , usuario);
        estudioService.save(estudio);
        consultaService.save(consulta);
        return new ResponseEntity<Mensaje>(new Mensaje("Solicitud verificada correctamente"), HttpStatus.OK);
    }
}
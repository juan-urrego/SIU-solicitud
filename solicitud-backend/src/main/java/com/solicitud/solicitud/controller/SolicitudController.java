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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    LineaGeneralService lineaGeneralService;

    @Autowired
    EstudioService estudioService;

    @Autowired
    ConsultaService consultaService;

    @Autowired
    ProyectoService proyectoService;

    @Autowired
    GrupoInvestigadorService grupoInvestigadorService;

    @Autowired
    ParametroConsultaService parametroConsultaService;

    @Autowired
    PrecotizacionService precotizacionService;
    
    @Autowired
    DetalleTramiteService detalleTramiteService;

    @Autowired
    ArgumentoService argumentoService;


    @GetMapping("/solicitudes")
    public ResponseEntity<?> list(){
        List<Solicitud> list = solicitudService.getSolicitudes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!solicitudService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una solicitud con esa id"), HttpStatus.NOT_FOUND);
        if(!solicitudService.getOne(id).isPresent())
            return new ResponseEntity<Mensaje>(new Mensaje("No tiene acceso a esta solicitud"), HttpStatus.FORBIDDEN);
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
        if(solicitudDto.getPrecotizacionDtos().contains(solicitudDto.getPrecotizacionDto()))
            return new ResponseEntity<Mensaje>(new Mensaje("La precotizacion elegida no coincide con las precotizaciones diligenciada"), HttpStatus.BAD_REQUEST);
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA).get();
        Usuario usuario = usuarioService.getByEmail(solicitudDto.getUsuarioEmail()).get();
        GrupoInvestigador grupoInvestigador = new GrupoInvestigador(solicitudDto.getCargo(), solicitudDto.getNombreContacto(), solicitudDto.getTelefonoContacto(), solicitudDto.getGrupo(), solicitudDto.getInvestigador(), solicitudDto.getProyecto());

        Solicitud solicitud = new Solicitud(solicitudDto.getTipoTramite(),
                solicitudDto.getNecesidad(),
                solicitudDto.getFecha(),
                solicitudDto.getValor(),
                solicitudDto.getVerificacion(),
                solicitudDto.getObservacion(),
                grupoInvestigador,
                estado,
                usuario
                );

        solicitudDto.getPrecotizacionDtos().forEach(precotizacion -> {
            precotizacion.setSolicitud(solicitud);
            solicitudDto.getDetalleTramiteDtos().forEach(detalleTramite -> {
                detalleTramite.setSolicitud(solicitud);
            });
            if((precotizacion.getValorTotal() == solicitudDto.getPrecotizacionDto().getValorTotal()) && (precotizacion.getValorIva() == solicitudDto.getPrecotizacionDto().getValorIva())){
                precotizacion.setArgumentos(solicitudDto.getArgumentoDtos());
                solicitud.setPrecotizacionElegida(precotizacion);
                solicitudDto.getArgumentoDtos().forEach(argumento -> {
                    argumento.setPrecotizacion(precotizacion);
                });
            }
        });

        Set<ProveedorDetalle> proveedorDetalles = new HashSet<>();

        solicitudDto.getDetalleTramiteDtos().forEach(detalleTramite -> {
            solicitudDto.getPrecotizacionDtos().forEach(precotizacion -> {
                ProveedorDetalle proveedorDetalle = new ProveedorDetalle(detalleTramite, precotizacion.getProveedor());
                proveedorDetalles.add(proveedorDetalle);
            });
            detalleTramite.setProveedorDetalles(proveedorDetalles);
            detalleTramite.setSolicitud(solicitud);
        });
        solicitud.setPrecotizaciones(solicitudDto.getPrecotizacionDtos());
        solicitud.setDetalleTramites(solicitudDto.getDetalleTramiteDtos());
        solicitudService.save(solicitud);
        return new ResponseEntity<Mensaje>(new Mensaje("Solicitud guardada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id") int id, @RequestBody SolicitudDto solicitudDto){
        if (!solicitudService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un solicitud con esa id"), HttpStatus.NOT_FOUND);
        if (solicitudDto.getPrecotizacionDtos().isEmpty())
            return new ResponseEntity<Mensaje>(new Mensaje("Debe poner por lo menos 1 precotizacion"), HttpStatus.BAD_REQUEST);
        if (solicitudDto.getArgumentoDtos().isEmpty())
            return new ResponseEntity<Mensaje>(new Mensaje("Debe poner por lo menos 1 argumento de la precotizacion elegida"), HttpStatus.BAD_REQUEST);
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA).get();
        Usuario usuario = usuarioService.getByEmail(solicitudDto.getUsuarioEmail()).get();
        GrupoInvestigador grupoInvestigador = new GrupoInvestigador(solicitudDto.getCargo(), solicitudDto.getNombreContacto(), solicitudDto.getTelefonoContacto(), solicitudDto.getGrupo(), solicitudDto.getInvestigador(), solicitudDto.getProyecto());

        Solicitud solicitud = solicitudService.getOne(id).get();
        solicitud.setTipoTramite(solicitudDto.getTipoTramite());
        solicitud.setNecesidad(solicitudDto.getNecesidad());
        solicitud.setFecha(solicitudDto.getFecha());
        solicitud.setValor(solicitudDto.getValor());
        solicitud.setVerificacion(solicitudDto.getVerificacion());
        solicitud.setObservacion(solicitudDto.getObservacion());
        solicitud.setGrupoInvestigador(grupoInvestigador);
        solicitud.setEstado(estado);
        solicitud.setUsuario(usuario);


        solicitudDto.getPrecotizacionDtos().forEach(precotizacion -> {
            precotizacion.setSolicitud(solicitud);
            solicitudDto.getDetalleTramiteDtos().forEach(detalleTramite -> {
                detalleTramite.setSolicitud(solicitud);
            });
            if((precotizacion.getValorTotal() == solicitudDto.getPrecotizacionDto().getValorTotal()) && (precotizacion.getValorIva() == solicitudDto.getPrecotizacionDto().getValorIva())){
                precotizacion.setArgumentos(solicitudDto.getArgumentoDtos());
                solicitud.setPrecotizacionElegida(precotizacion);
                solicitudDto.getArgumentoDtos().forEach(argumento -> {
                    argumento.setPrecotizacion(precotizacion);
                });
            }
        });

        Set<ProveedorDetalle> proveedorDetalles = new HashSet<>();

        solicitudDto.getDetalleTramiteDtos().forEach(detalleTramite -> {
            solicitudDto.getPrecotizacionDtos().forEach(precotizacion -> {
                ProveedorDetalle proveedorDetalle = new ProveedorDetalle(detalleTramite, precotizacion.getProveedor());
                proveedorDetalles.add(proveedorDetalle);
            });
            detalleTramite.setProveedorDetalles(proveedorDetalles);
            detalleTramite.setSolicitud(solicitud);
        });
        solicitud.setPrecotizaciones(solicitudDto.getPrecotizacionDtos());
        solicitud.setDetalleTramites(solicitudDto.getDetalleTramiteDtos());
        solicitudService.save(solicitud);
        return new ResponseEntity<Mensaje>(new Mensaje("Solicitud actualizada"), HttpStatus.OK);
    }

    @PostMapping("/confirmar/{id}")
    public ResponseEntity<Mensaje> confirmar(@PathVariable int id){
        if(!solicitudService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una solicitud con esa id"), HttpStatus.NOT_FOUND);
        Solicitud solicitud = solicitudService.getOne(id).get();
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.VERIFICADA).get();
        solicitud.setEstado(estado);
        solicitudService.save(solicitud);
        return new ResponseEntity<Mensaje>(new Mensaje("Solicitud verificada correctamente"), HttpStatus.OK);
    }

    @PostMapping("/crear/{id}")
    public ResponseEntity<Mensaje> createDocuments(@PathVariable int id){
        if (!solicitudService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una solicitud con esa id"), HttpStatus.NOT_FOUND);
        Solicitud solicitud = solicitudService.getOne(id).get();
        if (solicitud.getEstado().getEstadoNombre() == EstadoNombre.CREADA)
            return new ResponseEntity<Mensaje>(new Mensaje("El documento aún no está verificado para la creación de documentos"), HttpStatus.BAD_REQUEST);
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA).get();
        ParametroConsulta parametroConsulta = parametroConsultaService.getByParametro((byte) 1).get();
        ParametroAcuerdo parametroAcuerdo = parametroAcuerdoService.getByParametro((byte) 1).get();
        Usuario usuario = usuarioService.getOne(id).get();
        Consulta consulta = new Consulta(parametroConsulta.getDescripcion(), solicitud, estado);
        Estudio estudio = new Estudio(parametroAcuerdo.getDescripcion(), null, null, solicitud, null, estado);
        estudioService.save(estudio);
        consultaService.save(consulta);
        return new ResponseEntity<Mensaje>(new Mensaje("Documentos creados correctamente"), HttpStatus.OK);
    }
}
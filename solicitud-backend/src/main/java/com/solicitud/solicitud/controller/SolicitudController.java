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
        if(solicitudDto.getPrecotizacionDtos().contains(solicitudDto.getPrecotizacionDto()))
            return new ResponseEntity<Mensaje>(new Mensaje("La precotizacion elegida no coincide con las precotizaciones diligenciada"), HttpStatus.BAD_REQUEST);
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA).get();
        GrupoInvestigador grupoInvestigador = new GrupoInvestigador(solicitudDto.getCargo(), solicitudDto.getNombreContacto(), solicitudDto.getTelefonoContacto(), solicitudDto.getGrupo(), solicitudDto.getInvestigador(), solicitudDto.getProyecto());

        Solicitud solicitud = new Solicitud(solicitudDto.getTipoTramite(),
                solicitudDto.getNecesidad(),
                solicitudDto.getFecha(),
                solicitudDto.getValor(),
                solicitudDto.getVerificacion(),
                solicitudDto.getObservacion(),
                grupoInvestigador,
                estado
                );

        Set<Precotizacion> precotizaciones = new HashSet<>();
        solicitudDto.getPrecotizacionDtos().forEach(precotizacion -> {
            precotizacion.setSolicitud(solicitud);
            solicitudDto.getDetalleTramiteDtos().forEach(detalleTramite -> {
                detalleTramite.setSolicitud(solicitud);
            });
            if((precotizacion.getValorTotal() == solicitudDto.getPrecotizacionDto().getValorTotal()) && (precotizacion.getValorIva() == solicitudDto.getPrecotizacionDto().getValorIva())){
                precotizacion.setArgumentos(solicitudDto.getArgumentoDtos());
                solicitud.setPrecotizacionElegida(precotizacion);
            }
        });


//        solicitudDto.getPrecotizacionDtos().forEach((precotizacionx) ->{
//            Precotizacion precotizacion = new Precotizacion(precotizacionx.getValorTotal(), precotizacionx.getValorIva(), null, solicitud);
//            Proveedor proveedor;
//            if (precotizacionx.getProveedorId() == 0){
//                proveedor = new Proveedor(precotizacionx.getNombreProveedor(), precotizacionx.getNitProveedor(), precotizacionx.getTelefonoProveedor(), precotizacionx.getCiudadProveedor(), precotizacionx.getTipoIdentificacion());
//                proveedorService.save(proveedor);
//            }else{
//                proveedor = proveedorService.getOne(precotizacionx.getProveedorId()).get();
//            }
//            precotizacion.setProveedor(proveedor);
//            if((precotizacionx.getValorTotal() == solicitudDto.getPrecotizacionDto().getValorTotal()) && (precotizacionx.getValorIva() == solicitudDto.getPrecotizacionDto().getValorIva())){
//                Set<Argumento> argumentos = new HashSet<>();
//                solicitudDto.getArgumentoDtos().forEach((argumentosx) ->{
//                    solicitud.setPrecotizacionElegida(precotizacion);
//                    Argumento argumento = new Argumento(argumentosx.getDescripcion(), precotizacion);
//                    argumentos.add(argumento);
//                });
//                precotizacion.setArgumentos(argumentos);
//            }
//            precotizaciones.add(precotizacion);
//        });


        Set<DetalleTramite> detalleTramites = new HashSet<>();
        Set<ProveedorDetalle> proveedorDetalles = new HashSet<>();
//        solicitudDto.getDetalleTramiteDtos().forEach((detalleTramitesx) ->{
//            DetalleTramite detalleTramite = new DetalleTramite(detalleTramitesx.getDescripcion(), detalleTramitesx.getCantidad(), detalleTramitesx.getLineaGeneral(), solicitud, detalleTramitesx.getLineaEspecifica());
//            ProveedorDetalle proveedorDetalle = new ProveedorDetalle(detalleTramite, solicitud.getPrecotizacionElegida().getProveedor());
//            proveedorDetalles.add(proveedorDetalle);
//            detalleTramite.setProveedorDetalles(proveedorDetalles);
//            detalleTramites.add(detalleTramite);
//        });
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


        Set<Precotizacion> precotizaciones = new HashSet<>();
        Set<ProveedorDetalle> proveedorDetalles = new HashSet<>();
//        solicitudDto.getPrecotizacionDtos().forEach((precotizacionx) ->{
//            Proveedor proveedor1 = proveedorService.getOne(precotizacionx.getProveedorId()).get();
//            Precotizacion precotizacion = new Precotizacion(precotizacionx.getValorTotal(), precotizacionx.getValorIva(), proveedor1, solicitud);
//            if (precotizacionx.getProveedorId() == 0){
//                Proveedor proveedor = new Proveedor(precotizacionx.getNombreProveedor(), precotizacionx.getNitProveedor(), precotizacionx.getTelefonoProveedor(), precotizacionx.getCiudadProveedor(), precotizacionx.getTipoIdentificacion());
//                proveedorService.save(proveedor);
//                precotizacion.setProveedor(proveedor);
//            }
//            if((precotizacionx.getValorTotal() == solicitudDto.getPrecotizacionDto().getValorTotal()) && (precotizacionx.getValorIva() == solicitudDto.getPrecotizacionDto().getValorIva())){
//                Set<Argumento> argumentos = new HashSet<>();
//                solicitudDto.getArgumentoDtos().forEach((argumentosx) ->{
//                    solicitud.setPrecotizacionElegida(precotizacion);
//                    Argumento argumento = new Argumento(argumentosx.getDescripcion(), precotizacion);
//                    argumentos.add(argumento);
//                });
//                precotizacion.setArgumentos(argumentos);
//            }
//            precotizaciones.add(precotizacion);
//        });

        Set<DetalleTramite> detalleTramites = new HashSet<>();
        solicitudDto.getDetalleTramiteDtos().forEach((detalleTramitesx) ->{
//            LineaGeneral lineaGeneral = lineaGeneralService.getOne(detalleTramitesx.getLineaGeneral()).get();
            DetalleTramite detalleTramite = new DetalleTramite(detalleTramitesx.getDescripcion(), detalleTramitesx.getCantidad(), detalleTramitesx.getLineaGeneral(), solicitud, detalleTramitesx.getLineaEspecifica());
            ProveedorDetalle proveedorDetalle = new ProveedorDetalle(detalleTramite, solicitud.getPrecotizacionElegida().getProveedor());
            proveedorDetalles.add(proveedorDetalle);
            solicitud.getPrecotizacionElegida().getProveedor().setProveedorDetalles(proveedorDetalles);
            detalleTramites.add(detalleTramite);
        });
        solicitud.setPrecotizaciones(precotizaciones);
        solicitud.setDetalleTramites(detalleTramites);
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

    @PostMapping("/crear/{id}/{idUser}")
    public ResponseEntity<Mensaje> createDocuments(@PathVariable int id, @PathVariable int idUser){
        if (!solicitudService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe una solicitud con esa id"), HttpStatus.NOT_FOUND);
        if (!usuarioService.existsById(idUser))
            return new ResponseEntity<Mensaje>(new Mensaje("No existe un usuario con esa id"), HttpStatus.NOT_FOUND);
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
        return new ResponseEntity<Mensaje>(new Mensaje("Documentos creados correctamente"), HttpStatus.OK);
    }
}
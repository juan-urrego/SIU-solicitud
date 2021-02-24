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
    LineaProductoService lineaProductoService;

    @Autowired
    EstudioService estudioService;

    @Autowired
    ConsultaService consultaService;

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
        Grupo grupo = grupoService.getOne(solicitudDto.getGrupo()).get();
        Investigador investigador = investigadorService.getOne(solicitudDto.getInvestigador()).get();
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA).get();
        GrupoInvestigador grupoInvestigador = new GrupoInvestigador(solicitudDto.getCargo(), solicitudDto.getNombreContacto(), solicitudDto.getTelefonoContacto(), grupo, investigador);

        Solicitud solicitud = new Solicitud(solicitudDto.getTipoTramite(),
                solicitudDto.getNecesidad(),
                solicitudDto.getFecha(),
                solicitudDto.getValor(),
                solicitudDto.getVerificacion(),
                solicitudDto.getObservacion(),
                grupoInvestigador,
                estado
                );
        Set<DetalleTramite> detalleTramites = new HashSet<>();
        solicitudDto.getDetalleTramiteDtos().forEach((detalleTramitesx) ->{
            LineaProducto lineaProducto = lineaProductoService.getOne(detalleTramitesx.getLineaProducto()).get();
            DetalleTramite detalleTramite = new DetalleTramite(detalleTramitesx.getDescripcion(), detalleTramitesx.getCantidad(), lineaProducto, solicitud);
            detalleTramites.add(detalleTramite);
        });

        Set<Precotizacion> precotizaciones = new HashSet<>();
        solicitudDto.getPrecotizacionDtos().forEach((precotizacionx) ->{
            Precotizacion precotizacion = new Precotizacion(precotizacionx.getValorTotal(), precotizacionx.getValorIva(), precotizacionx.getProveedor(), solicitud);
            if (precotizacionx.getProveedor().getId() == 0){
                Proveedor proveedor = new Proveedor(precotizacionx.getProveedor().getNombre(), precotizacionx.getProveedor().getNit(), precotizacionx.getProveedor().getTelefono(), precotizacionx.getProveedor().getCiudad());
                proveedorService.save(proveedor);
                precotizacion.setProveedor(proveedor);
            }
            if((precotizacionx.getValorTotal() == solicitudDto.getPrecotizacionDto().getValorTotal()) && (precotizacionx.getValorIva() == solicitudDto.getPrecotizacionDto().getValorIva())){
                Set<Argumento> argumentos = new HashSet<>();
                solicitudDto.getArgumentoDtos().forEach((argumentosx) ->{
                    solicitud.setPrecotizacionElegida(precotizacion);
                    Argumento argumento = new Argumento(argumentosx.getDescripcion(), precotizacion);
                    argumentos.add(argumento);
                });
                precotizacion.setArgumentos(argumentos);
            }
            precotizaciones.add(precotizacion);
        });
        solicitud.setPrecotizaciones(precotizaciones);
        solicitud.setDetalleTramites(detalleTramites);
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
        Grupo grupo = grupoService.getOne(solicitudDto.getGrupo()).get();
        Investigador investigador = investigadorService.getOne(solicitudDto.getInvestigador()).get();
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA).get();
        GrupoInvestigador grupoInvestigador = new GrupoInvestigador(solicitudDto.getCargo(), solicitudDto.getNombreContacto(), solicitudDto.getTelefonoContacto(), grupo, investigador);

        Solicitud solicitud = solicitudService.getOne(id).get();
        solicitud.setTipoTramite(solicitudDto.getTipoTramite());
        solicitud.setNecesidad(solicitudDto.getNecesidad());
        solicitud.setFecha(solicitudDto.getFecha());
        solicitud.setValor(solicitudDto.getValor());
        solicitud.setVerificacion(solicitudDto.getVerificacion());
        solicitud.setObservacion(solicitudDto.getObservacion());
        solicitud.setGrupoInvestigador(grupoInvestigador);
        solicitud.setEstado(estado);

        Set<DetalleTramite> detalleTramites = new HashSet<>();
        solicitudDto.getDetalleTramiteDtos().forEach((detalleTramitesx) ->{
            LineaProducto lineaProducto = lineaProductoService.getOne(detalleTramitesx.getLineaProducto()).get();
            DetalleTramite detalleTramite = new DetalleTramite(detalleTramitesx.getDescripcion(), detalleTramitesx.getCantidad(), lineaProducto, solicitud);
            detalleTramites.add(detalleTramite);
        });

        Set<Precotizacion> precotizaciones = new HashSet<>();
        solicitudDto.getPrecotizacionDtos().forEach((precotizacionx) ->{
            Precotizacion precotizacion = new Precotizacion(precotizacionx.getValorTotal(), precotizacionx.getValorIva(), precotizacionx.getProveedor(), solicitud);
            if (precotizacionx.getProveedor().getId() == 0){
                Proveedor proveedor = new Proveedor(precotizacionx.getProveedor().getNombre(), precotizacionx.getProveedor().getNit(), precotizacionx.getProveedor().getTelefono(), precotizacionx.getProveedor().getCiudad());
                proveedorService.save(proveedor);
                precotizacion.setProveedor(proveedor);
            }
            if((precotizacionx.getValorTotal() == solicitudDto.getPrecotizacionDto().getValorTotal()) && (precotizacionx.getValorIva() == solicitudDto.getPrecotizacionDto().getValorIva())){
                Set<Argumento> argumentos = new HashSet<>();
                solicitudDto.getArgumentoDtos().forEach((argumentosx) ->{
                    solicitud.setPrecotizacionElegida(precotizacion);
                    Argumento argumento = new Argumento(argumentosx.getDescripcion(), precotizacion);
                    argumentos.add(argumento);
                });
                precotizacion.setArgumentos(argumentos);
            }
            precotizaciones.add(precotizacion);
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
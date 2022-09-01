package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.Mail;
import com.solicitud.solicitud.dto.SolicitudDto;
import com.solicitud.solicitud.entity.*;
import com.solicitud.solicitud.enums.EstadoNombre;
import com.solicitud.solicitud.repository.SolicitudRepository;
import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SolicitudService {

    final
    SolicitudRepository solicitudRepository;

    final
    UserService userService;

    final
    EstadoService estadoService;

    final
    ParametroConsultaService parametroConsultaService;

    final
    ParametroAcuerdoService parametroAcuerdoService;

    final
    EstudioService estudioService;

    final
    ConsultaService consultaService;

    final
    SendMailService sendMailService;

    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository, UserService userService, EstadoService estadoService, ParametroConsultaService parametroConsultaService, ParametroAcuerdoService parametroAcuerdoService, EstudioService estudioService, ConsultaService consultaService, SendMailService sendMailService) {
        this.solicitudRepository = solicitudRepository;
        this.userService = userService;
        this.estadoService = estadoService;
        this.parametroConsultaService = parametroConsultaService;
        this.parametroAcuerdoService = parametroAcuerdoService;
        this.estudioService = estudioService;
        this.consultaService = consultaService;
        this.sendMailService = sendMailService;
    }

    public Solicitud getSolicitudById(int id){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            return solicitudRepository.findByIdAndUser(id, user).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no acces to this document"));
        }
        return solicitudRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "document does not exist with this id, or not found"));
    }


    public List<Solicitud> getAll(){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            return solicitudRepository.findAllByUser(user);
        }
        return solicitudRepository.findAll();
    }

    public Solicitud save(Solicitud solicitud){
        return solicitudRepository.save(solicitud);
    }

    public void saveSolicitud(SolicitudDto solicitudDto) {
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA);
        User user = userService.getUserByEmail(solicitudDto.getUsuarioEmail());
        GrupoInvestigador grupoInvestigador = new GrupoInvestigador(solicitudDto.getCargo(), solicitudDto.getNombreContacto(), solicitudDto.getTelefonoContacto(), solicitudDto.getGrupo(), solicitudDto.getInvestigador(), solicitudDto.getProyecto());
        Solicitud solicitud = new Solicitud(solicitudDto.getTipoTramite(),
                solicitudDto.getNecesidad(),
                solicitudDto.getFecha(),
                solicitudDto.getValor(),
                solicitudDto.getVerificacion(),
                solicitudDto.getObservacion(),
                grupoInvestigador,
                estado,
                user
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
        save(solicitud);
    }

    public void updated(int id, SolicitudDto solicitudDto) {
        Solicitud solicitud = getSolicitudById(id);
        if (!solicitud.getEstado().getEstadoNombre().equals(EstadoNombre.POR_VERIFICAR) &&
            !solicitud.getEstado().getEstadoNombre().equals(EstadoNombre.CREADA))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "this document can not be updated again");
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA);
        User user = userService.getUserByEmail(solicitudDto.getUsuarioEmail());
        GrupoInvestigador grupoInvestigador = new GrupoInvestigador(solicitudDto.getCargo(), solicitudDto.getNombreContacto(), solicitudDto.getTelefonoContacto(), solicitudDto.getGrupo(), solicitudDto.getInvestigador(), solicitudDto.getProyecto());
        solicitud.setTipoTramite(solicitudDto.getTipoTramite());
        solicitud.setNecesidad(solicitudDto.getNecesidad());
        solicitud.setFecha(solicitudDto.getFecha());
        solicitud.setValor(solicitudDto.getValor());
        solicitud.setVerificacion(solicitudDto.getVerificacion());
        solicitud.setObservacion(solicitudDto.getObservacion());
        solicitud.setGrupoInvestigador(grupoInvestigador);
        solicitud.setEstado(estado);
        solicitud.setUser(user);


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
        save(solicitud);
    }

    public void verifySolicitud(int id) {
        Solicitud solicitud = getSolicitudById(id);
        if (!solicitud.getEstado().getEstadoNombre().equals(EstadoNombre.CREADA))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "document must be just created");
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.VERIFICADA);
        solicitud.setEstado(estado);
        save(solicitud);
    }

    public void createDocuments(int id) {
        Solicitud solicitud = getSolicitudById(id);
        if (!solicitud.getEstado().getEstadoNombre().equals(EstadoNombre.VERIFICADA))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "document must be verified");
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.CREADA);
        User director = userService.getDirectorActive().orElse(null);
        ParametroConsulta parametroConsulta = parametroConsultaService.getParametroActivo((byte) 1);
        ParametroAcuerdo parametroAcuerdo = parametroAcuerdoService.getParametroActivo((byte) 1);
        Consulta consulta = new Consulta(parametroConsulta.getDescripcion(), solicitud, estado);
        Estudio estudio = new Estudio(parametroAcuerdo.getDescripcion(), solicitud, director, estado);
        estudioService.save(estudio);
        consultaService.save(consulta);
        solicitud.setEstado(estadoService.getByEstadoNombre(EstadoNombre.DOCUMENTADA));
        save(solicitud);
    }

    public void sendMail(int id, Mail mail){
        Solicitud solicitud = getSolicitudById(id);
        if (!solicitud.getEstado().getEstadoNombre().equals(EstadoNombre.CREADA))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "document must be just created");
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.POR_VERIFICAR);
        String message = mail.getCuerpo() +"\n\n Datos de contacto: " + "\nNombre: " + mail.getNombre() + "\nE-mail: " + mail.getOriginEmail();
        sendMailService.sendMail("juanurrego21277@gmail.com",mail.getDestinyEmail(), mail.getAsunto(),message);
        solicitud.setEstado(estado);
        save(solicitud);
    }

    public void delete(int id){
        Solicitud solicitud = getSolicitudById(id);
        solicitudRepository.delete(solicitud);
    }

}
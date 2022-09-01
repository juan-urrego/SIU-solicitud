package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.entity.Estado;
import com.solicitud.solicitud.enums.EstadoNombre;
import com.solicitud.solicitud.repository.ConsultaRepository;
import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ConsultaService {

    final
    ConsultaRepository consultaRepository;

    final
    UserService userService;

    final
    EstadoService estadoService;

    public ConsultaService(ConsultaRepository consultaRepository, UserService userService, EstadoService estadoService) {
        this.consultaRepository = consultaRepository;
        this.userService = userService;
        this.estadoService = estadoService;
    }

    public Consulta getConsultaById(int id){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            return consultaRepository.findByIdAndSolicitud_User(id, user).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no access to this consult document"));
        }
        return consultaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "consult does not exist with this id, or not found"));
    }

    public List<Consulta> getll(){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            return consultaRepository.findAllBySolicitud_User(user);
        }
        return consultaRepository.findAll();
    }

    public void save(Consulta consulta){
        consultaRepository.save(consulta);
    }

    public void confirmarConsulta(int id) {
        Consulta consulta = getConsultaById(id);
        Estado estado = estadoService.getByEstadoNombre(EstadoNombre.VERIFICADA);
        consulta.setEstado(estado);
        save(consulta);
    }
}

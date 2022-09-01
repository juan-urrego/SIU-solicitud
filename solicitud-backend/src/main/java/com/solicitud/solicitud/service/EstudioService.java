package com.solicitud.solicitud.service;

import com.solicitud.solicitud.dto.EstudioDto;
import com.solicitud.solicitud.entity.Estado;
import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.enums.EstadoNombre;
import com.solicitud.solicitud.repository.EstudioRepository;
import com.solicitud.solicitud.security.entity.Role;
import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.enums.RoleName;
import com.solicitud.solicitud.security.service.RoleService;
import com.solicitud.solicitud.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstudioService {

    final
    EstudioRepository estudioRepository;

    final
    UserService userService;

    final
    EstadoService estadoService;

    final RoleService roleService;

    @Autowired
    public EstudioService(EstudioRepository estudioRepository, UserService userService, EstadoService estadoService, RoleService roleService) {
        this.estudioRepository = estudioRepository;
        this.userService = userService;
        this.estadoService = estadoService;
        this.roleService = roleService;
    }

    public Estudio getEstudioById(int id){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            return estudioRepository.findByIdAndSolicitud_User(id, user).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no access to this study document"));
        }
        return estudioRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "study does not exist with this id, or not found"));
    }

    public List<Estudio> getAll(){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            return estudioRepository.findAllBySolicitud_User(user);
        }
        return estudioRepository.findAll();
    }

    public void save(final Estudio estudio){
        estudioRepository.save(estudio);
    }

    public void update(int id, EstudioDto estudioDto) {
        Estudio estudio = getEstudioById(id);
        estudio.setUnidadAcademica(estudioDto.getUnidadAcademica());
        if (estudioDto.isFirmaUsuario()){
            Estado estado = estadoService.getByEstadoNombre(EstadoNombre.ADMIN_FIRMADO);
            Optional<Role> roleAdmin = roleService.getByRoleName(RoleName.ROLE_DIRECTOR);
            estudio.setFirmaUsuario(userService.getUserByRoleActive(roleAdmin).get().getSignatureUrl());
            estudio.setEstado(estado);
        }
        if (estudioDto.isFirmaDirector()){
            Estado estado = estadoService.getByEstadoNombre(EstadoNombre.DIRECTOR_FIRMADO);
            estudio.setFirmaDirector(estudio.getDirector().getSignatureUrl());
            estudio.setEstado(estado);
        }
        if (estudio.getUnidadAcademica() != null &&
                estudio.getFirmaDirector() != null &&
                estudio.getFirmaUsuario() != null){
            Estado estado = estadoService.getByEstadoNombre(EstadoNombre.FIRMADO);
            estudio.setEstado(estado);
        }
        save(estudio);
    }

}

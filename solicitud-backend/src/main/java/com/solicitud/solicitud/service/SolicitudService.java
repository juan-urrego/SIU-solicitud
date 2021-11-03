package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Solicitud;
import com.solicitud.solicitud.repository.SolicitudRepository;
import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.jwt.JwtEntryPoint;
import com.solicitud.solicitud.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class SolicitudService {

    final
    SolicitudRepository solicitudRepository;

    final
    UserService userService;


    public SolicitudService(SolicitudRepository solicitudRepository, UserService userService) {
        this.solicitudRepository = solicitudRepository;
        this.userService = userService;
    }

    public Optional<Solicitud> getOne(int id){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            return solicitudRepository.findByIdAndUser(id, user);
        }
        return solicitudRepository.findById(id);
    }

    public boolean existsById(final int id){
        return solicitudRepository.existsById(id);
    }


    public List<Solicitud> getSolicitudes(){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            return solicitudRepository.findAllByUser(user);
        }
        return solicitudRepository.findAll();
    }

    public Solicitud save(final Solicitud solicitud){
        return solicitudRepository.save(solicitud);
    }

    public void delete(int id){
        solicitudRepository.deleteById(id);
    }

}
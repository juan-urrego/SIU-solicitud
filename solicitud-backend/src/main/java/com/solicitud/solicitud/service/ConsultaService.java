package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.repository.ConsultaRepository;
import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConsultaService {

    final
    ConsultaRepository consultaRepository;

    final
    UserService userService;

    public ConsultaService(ConsultaRepository consultaRepository, UserService userService) {
        this.consultaRepository = consultaRepository;
        this.userService = userService;
    }

    public boolean existsById(final int id){
        return consultaRepository.existsById(id);
    }

    public Optional<Consulta> getOne(int id){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            return consultaRepository.findByIdAndSolicitud_User(id, user);
        }
        return consultaRepository.findById(id);
    }

    public List<Consulta> getConsultas(){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            return consultaRepository.findAllBySolicitud_User(user);
        }
        return consultaRepository.findAll();
    }

    public void save(final Consulta consulta){
        consultaRepository.save(consulta);
    }

    public void delete(int id){
        consultaRepository.deleteById(id);
    }


}

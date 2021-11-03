package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.repository.EstudioRepository;
import com.solicitud.solicitud.security.entity.User;
import com.solicitud.solicitud.security.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstudioService {

    final
    EstudioRepository estudioRepository;

    final
    UserService userService;

    public EstudioService(EstudioRepository estudioRepository, UserService userService) {
        this.estudioRepository = estudioRepository;
        this.userService = userService;
    }

    public boolean existsById(final int id){
        return estudioRepository.existsById(id);
    }

    public Optional<Estudio> getOne(int id){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            return estudioRepository.findByIdAndSolicitud_User(id, user);
        }
        return estudioRepository.findById(id);
    }

    public List<Estudio> getEstudios(){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(o -> o.getAuthority().equals("ROLE_USER"))){
            User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            return estudioRepository.findAllBySolicitud_User(user);
        }
        return estudioRepository.findAll();
    }

    public void save(final Estudio estudio){
        estudioRepository.save(estudio);
    }

    public void delete(int id){
        estudioRepository.deleteById(id);
    }

}

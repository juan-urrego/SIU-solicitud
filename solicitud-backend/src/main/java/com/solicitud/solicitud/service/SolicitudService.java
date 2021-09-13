package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Solicitud;
import com.solicitud.solicitud.repository.SolicitudRepository;
import com.solicitud.solicitud.security.entity.Usuario;
import com.solicitud.solicitud.security.service.UsuarioService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SolicitudService {

    final
    SolicitudRepository solicitudRepository;

    final
    UsuarioService usuarioService;

    public SolicitudService(SolicitudRepository solicitudRepository, UsuarioService usuarioService) {
        this.solicitudRepository = solicitudRepository;
        this.usuarioService = usuarioService;
    }

    public Optional<Solicitud> getOne(int id){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().size() == 1) {
            if (usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()) {
                Usuario usuario = usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
                return solicitudRepository.findByIdAndUsuario(id, usuario);
            }
        }
        return solicitudRepository.findById(id);
    }

    public boolean existsById(final int id){
        return solicitudRepository.existsById(id);
    }


    public List<Solicitud> getSolicitudes(){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().size() != 1)
            return solicitudRepository.findAll();
        if (usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()){
            Usuario usuario = usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            return solicitudRepository.findAllByUsuario(usuario);
        }
        return null;
    }

    public Solicitud save(final Solicitud solicitud){
        return solicitudRepository.save(solicitud);
    }

    public void delete(int id){
        solicitudRepository.deleteById(id);
    }

}
package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.repository.EstudioRepository;
import com.solicitud.solicitud.security.entity.Usuario;
import com.solicitud.solicitud.security.service.UsuarioService;
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
    UsuarioService usuarioService;

    public EstudioService(EstudioRepository estudioRepository, UsuarioService usuarioService) {
        this.estudioRepository = estudioRepository;
        this.usuarioService = usuarioService;
    }

    public boolean existsById(final int id){
        return estudioRepository.existsById(id);
    }

    public Optional<Estudio> getOne(int id){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().size() == 1) {
            if (usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()) {
                Usuario usuario = usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
                return estudioRepository.findByIdAndSolicitud_Usuario(id, usuario);
            }
        }
        return estudioRepository.findById(id);
    }

    public List<Estudio> getEstudios(){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().size() != 1)
            return estudioRepository.findAll();
        if (usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()){
            Usuario usuario = usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            return estudioRepository.findAllBySolicitud_Usuario(usuario);
        }
        return null;
    }

    public void save(final Estudio estudio){
        estudioRepository.save(estudio);
    }

    public void delete(int id){
        estudioRepository.deleteById(id);
    }

}

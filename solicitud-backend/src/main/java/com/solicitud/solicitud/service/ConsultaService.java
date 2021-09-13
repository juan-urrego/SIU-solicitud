package com.solicitud.solicitud.service;

import com.solicitud.solicitud.entity.Consulta;
import com.solicitud.solicitud.repository.ConsultaRepository;
import com.solicitud.solicitud.security.entity.Usuario;
import com.solicitud.solicitud.security.service.UsuarioService;
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
    UsuarioService usuarioService;

    public ConsultaService(ConsultaRepository consultaRepository, UsuarioService usuarioService) {
        this.consultaRepository = consultaRepository;
        this.usuarioService = usuarioService;
    }

    public boolean existsById(final int id){
        return consultaRepository.existsById(id);
    }

    public Optional<Consulta> getOne(int id){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().size() == 1) {
            if (usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()) {
                Usuario usuario = usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
                return consultaRepository.findByIdAndSolicitud_Usuario(id, usuario);
            }
        }
        return consultaRepository.findById(id);
    }

    public List<Consulta> getConsultas(){
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().size() != 1)
            return consultaRepository.findAll();
        if (usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).isPresent()){
            Usuario usuario = usuarioService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
            return consultaRepository.findAllBySolicitud_Usuario(usuario);
        }
        return null;
    }

    public void save(final Consulta consulta){
        consultaRepository.save(consulta);
    }

    public void delete(int id){
        consultaRepository.deleteById(id);
    }


}

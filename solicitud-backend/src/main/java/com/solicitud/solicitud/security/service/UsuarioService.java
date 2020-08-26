package com.solicitud.solicitud.security.service;

import com.solicitud.solicitud.security.entity.Usuario;
import com.solicitud.solicitud.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByEmail(final String email){
        return usuarioRepository.findByEmail(email);
    }


    public boolean existsByEmail(final String email){
        return usuarioRepository.existsByEmail(email);
    }

    public boolean existsById(final int id){
        return usuarioRepository.existsById(id);
    }

    public Optional<Usuario> getOne(int id){
        return usuarioRepository.findById(id);
    }

    public List<Usuario> getUsuario(){
        final List<Usuario> usuarios;
        usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public void save(final Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public void delete(int id){
        usuarioRepository.deleteById(id);
    }



}

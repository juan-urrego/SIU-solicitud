package com.solicitud.solicitud.security.service;

import com.solicitud.solicitud.security.entity.Usuario;
import com.solicitud.solicitud.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public Optional<Usuario> getByEmail(String email){
        return repository.findByEmail(email);
    }

    public boolean existByEmail(String email){
        return repository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        repository.save(usuario);
    }
}

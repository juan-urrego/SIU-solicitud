package com.solicitud.solicitud.security.service;

import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.repository.FileSystemRepository;
import com.solicitud.solicitud.security.entity.Rol;
import com.solicitud.solicitud.security.entity.Usuario;
import com.solicitud.solicitud.security.enums.RolNombre;
import com.solicitud.solicitud.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    FileSystemRepository fileSystemRepository;

    @Autowired
    RolService rolService;

    public Optional<Usuario> getByEmail(final String email){
        return usuarioRepository.findByEmail(email);
    }


    public Usuario getDirectorActivo() {
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_DIRECTOR).get());
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        return usuarioRepository.findByActivoAndRolesIn(true, roles).orElse(null);
    }

    public Usuario getUserByFirma(String firma){
        return usuarioRepository.findByFirma(firma).orElse(null);
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

    public String saveImage(byte[] bytes, String nombre) throws Exception{
        return fileSystemRepository.saveImageFileSystem(bytes, nombre);
    }

    //encontrar imagen por Id
    public FileSystemResource findImageById(int id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return fileSystemRepository.findInFileSystem(usuario.getFirma());
    }

    public FileSystemResource findFirma(String firma) {
        return fileSystemRepository.findInFileSystem(firma);
    }

    public void deleteImageByPath(String location){
        fileSystemRepository.deleteByPath(location);
    }

    public void save(final Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public void delete(int id){
        usuarioRepository.deleteById(id);
    }



}

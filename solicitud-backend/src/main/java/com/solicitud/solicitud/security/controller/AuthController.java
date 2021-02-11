package com.solicitud.solicitud.security.controller;

import com.solicitud.solicitud.dto.Mensaje;
import com.solicitud.solicitud.security.dto.JwtDto;
import com.solicitud.solicitud.security.dto.LoginUsuario;
import com.solicitud.solicitud.security.dto.NuevoUsuario;
import com.solicitud.solicitud.security.entity.Rol;
import com.solicitud.solicitud.security.entity.Usuario;
import com.solicitud.solicitud.security.enums.RolNombre;
import com.solicitud.solicitud.security.jwt.JwtProvider;
import com.solicitud.solicitud.security.service.RolService;
import com.solicitud.solicitud.security.service.UsuarioService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Mensaje>(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity<Mensaje>(new Mensaje("este email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()), nuevoUsuario.getFirma());
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity<Mensaje>(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<Mensaje>(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        if (!usuarioService.existsByEmail(loginUsuario.getEmail()))
            return new ResponseEntity<Mensaje>(new Mensaje("Email o contraseña invalidos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
    }

    @GetMapping("/auxiliares")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> list(){
        List<Usuario> list = usuarioService.getUsuario();
        return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
    }

    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/auxiliar/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        if(!usuarioService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("no existe id"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioService.getOne(id).get();
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mensaje> delete(@PathVariable("id")int id){
        if(!usuarioService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("no existe esa id"), HttpStatus.NOT_FOUND);
        usuarioService.delete(id);
        return new ResponseEntity<Mensaje>(new Mensaje("producto eliminado"), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Mensaje> update(@PathVariable("id")int id, @RequestBody NuevoUsuario nuevoUsuario){
        if(!usuarioService.existsById(id))
            return new ResponseEntity<Mensaje>(new Mensaje("no existe con esa id"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(nuevoUsuario.getEmail()))
            return new ResponseEntity<Mensaje>(new Mensaje("el email es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(nuevoUsuario.getPassword()))
            return new ResponseEntity<Mensaje>(new Mensaje("La contraseña es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(nuevoUsuario.getFirma()))
            return new ResponseEntity<Mensaje>(new Mensaje("La Firma es obligatoria"), HttpStatus.BAD_REQUEST);
        Usuario usuario = usuarioService.getOne(id).get();
        usuario.setNombre(nuevoUsuario.getNombre());
        usuario.setApellido(nuevoUsuario.getApellido());
        usuario.setEmail(nuevoUsuario.getEmail());
        usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
        usuario.setFirma(nuevoUsuario.getFirma());
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity<Mensaje>(new Mensaje("producto actualizado"), HttpStatus.OK);
    }
}

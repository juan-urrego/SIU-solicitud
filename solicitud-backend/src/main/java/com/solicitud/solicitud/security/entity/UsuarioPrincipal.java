package com.solicitud.solicitud.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails {
    private String nombre;
    private String email;
    private String cargo;
    private boolean activo;
    private String password;
    private String firma;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String nombre, String email, String cargo, boolean activo, String password, String firma, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.email = email;
        this.cargo = cargo;
        this.activo = activo;
        this.password = password;
        this.firma = firma;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                .getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getNombre(), usuario.getEmail(), usuario.getCargo(), usuario.isActivo(), usuario.getPassword(), usuario.getFirma(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getFirma() {
        return firma;
    }
}

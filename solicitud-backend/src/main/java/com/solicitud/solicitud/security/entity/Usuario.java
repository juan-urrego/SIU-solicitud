package com.solicitud.solicitud.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.entity.Solicitud;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private int id;
    @NotNull
    @Column(name = "usr_nombre")
    private String nombre;
    @NotNull
    @Column(name = "usr_email")
    private String email;
    @NotNull
    @Column(name = "usr_cargo")
    private String cargo;
    @NotNull
    @Column(name = "usr_activo")
    private boolean activo;
    @NotNull
    @JsonIgnore
    @Column(name = "usr_password")
    private String password;
    @NotNull
    @Column(name = "usr_firma")
    private String firma;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private Set<Solicitud> solicitudes;

    public Usuario() {
    }

    public Usuario(@NotNull String nombre, @NotNull String email, @NotNull String cargo, @NotNull boolean activo, @NotNull String password, @NotNull String firma) {
        this.nombre = nombre;
        this.email = email;
        this.cargo = cargo;
        this.activo = activo;
        this.password = password;
        this.firma = firma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public Set<Rol> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}

package com.solicitud.solicitud.security.entity;

import com.solicitud.solicitud.entity.Estudio;

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
    @Column(name = "usr_apellido")
    private String apellido;
    @NotNull
    @Column(name = "usr_email")
    private String email;
    @NotNull
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
    private Set<Estudio> estudios;

    public Usuario() {
    }

    public Usuario(@NotNull String nombre, @NotNull String apellido, @NotNull String email, @NotNull String password, @NotNull String firma) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

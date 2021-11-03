package com.solicitud.solicitud.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solicitud.solicitud.entity.Estudio;
import com.solicitud.solicitud.entity.Solicitud;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private int id;
    @NotNull
    @Column(name = "usr_nombre")
    private String name;
    @Email(message = "it must to be an email")
    @NotBlank
    @Column(name = "usr_email", nullable = false, unique = true)
    private String email;
    @NotNull
    @Column(name = "usr_cargo")
    private String position;
    @NotNull
    @Column(name = "usr_activo")
    private boolean active;
    @NotNull
    @JsonIgnore
    @Column(name = "usr_password")
    private String password;
    @NotNull
    @Column(name = "usr_firma")
    private String signatureUrl;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Solicitud> solicitudes;

    @OneToMany(mappedBy = "director")
    @JsonIgnore
    private Set<Estudio> estudios;

    public User() {
    }

    public User(@NotNull String name, @NotNull String email, @NotNull String position, @NotNull boolean active,
                    @NotNull String password, @NotNull String signatureUrl) {
        this.name = name;
        this.email = email;
        this.position = position;
        this.active = active;
        this.password = password;
        this.signatureUrl = signatureUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignatureUrl() {
        return signatureUrl;
    }

    public void setSignatureUrl(String signatureUrl) {
        this.signatureUrl = signatureUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}

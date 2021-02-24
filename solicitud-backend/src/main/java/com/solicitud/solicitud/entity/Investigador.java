package com.solicitud.solicitud.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "investigadores")
public class Investigador {

    @Id
    @Column(name = "inv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(name = "inv_identificacion", unique = true)
    private String identificacion;
    @NotNull
    @Column(name = "inv_nombre")
    private String nombre;
    @NotNull
    @Column(name = "inv_telefono")
    private String telefono;
    @NotNull
    @Column(name = "inv_email")
    private String email;
    @NotNull
    @Column(name = "inv_firma")
    private String firma;
    @NotNull
    @Column(name = "inv_type")
    private String type;
    @NotNull
    @Column(name = "inv_picByte", length = 1000)
    private byte[] picByte;

    @OneToMany(mappedBy = "investigador")
    @JsonIgnore
    private Set<GrupoInvestigador> grupoInvestigadores;

    public Investigador(@NotNull String identificacion, @NotNull String nombre, @NotNull String telefono, @NotNull String email, @NotNull String firma, @NotNull String type, @NotNull byte[] picByte) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.firma = firma;
        this.type = type;
        this.picByte = picByte;
    }

    public Investigador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<GrupoInvestigador> getGrupoInvestigadores() {
        return grupoInvestigadores;
    }

    public void setGrupoInvestigadores(Set<GrupoInvestigador> grupoInvestigadores) {
        this.grupoInvestigadores = grupoInvestigadores;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public byte[] getPicByte() {
        return picByte;
    }
    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }
}



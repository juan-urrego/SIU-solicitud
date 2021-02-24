package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_id")
    private int id;
    @NotNull
    @Column(name = "pr_nombre")
    private String nombre;
    @Column(name = "pr_codigo_proyecto")
    @NotNull
    private String codigoProyecto;
    @NotNull
    @Column(name = "pr_centro_costos")
    private String centoCostos;

    @ManyToOne
    @JsonIgnoreProperties("proyectos")
    @JoinColumn(name = "pr_grupo_id")
    private Grupo grupo;

    public Proyecto() {
    }

    public Proyecto(@NotNull String nombre, @NotNull String codigoProyecto, @NotNull String centoCostos, Grupo grupo) {
        this.nombre = nombre;
        this.codigoProyecto = codigoProyecto;
        this.centoCostos = centoCostos;
        this.grupo = grupo;
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

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public String getCentoCostos() {
        return centoCostos;
    }

    public void setCentoCostos(String centoCostos) {
        this.centoCostos = centoCostos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}

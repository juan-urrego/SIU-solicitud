package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @Column(name = "gr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(name = "gr_nombre")
    private String nombre;
    @NotNull
    @Column(name = "gr_cod_colciencia")
    private String codColciencia;

    @OneToMany(mappedBy = "grupo")
    Set<Proyecto> proyectos;

    @OneToMany(mappedBy = "grupo")
    Set<GrupoInvestigador> grupoInvestigadores;

    public Grupo(@NotNull String nombre, @NotNull String codColciencia) {
        this.nombre = nombre;
        this.codColciencia = codColciencia;
    }

    public Grupo() {
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

    public String getCodColciencia() {
        return codColciencia;
    }

    public void setCodColciencia(String codColciencia) {
        this.codColciencia = codColciencia;
    }

    public Set<GrupoInvestigador> getGrupoInvestigadores() {
        return grupoInvestigadores;
    }

    public void setGrupoInvestigadores(Set<GrupoInvestigador> grupoInvestigadores) {
        this.grupoInvestigadores = grupoInvestigadores;
    }

    public Set<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Set<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
}

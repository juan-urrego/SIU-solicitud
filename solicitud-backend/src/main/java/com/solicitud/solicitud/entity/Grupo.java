package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(
        name = "grupos",
        uniqueConstraints = {
                @UniqueConstraint(name = "gr_codigo_unique", columnNames = "gr_codigo")
        }
)
public class Grupo {

    @Id
    @Column(name = "gr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "gr_codigo", nullable = false)
    private String codigoGrupo;
    @Column(name = "gr_nombre", nullable = false)
    private String nombre;
    @Column(name = "gr_cod_colciencia", nullable = false)
    private String codigoColciencia;

    @JsonIgnoreProperties("grupo")
    @OneToMany(mappedBy = "grupo")
    Set<Proyecto> proyectos;

    @JsonIgnore
    @OneToMany(mappedBy = "grupo")
    Set<GrupoInvestigador> grupoInvestigadores;


    public Grupo(@NotNull String codigoGrupo, @NotNull String nombre, @NotNull String codigoColciencia) {
        this.codigoGrupo = codigoGrupo;
        this.nombre = nombre;
        this.codigoColciencia = codigoColciencia;
    }

    public Grupo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoColciencia() {
        return codigoColciencia;
    }

    public void setCodigoColciencia(String codigoColciencia) {
        this.codigoColciencia = codigoColciencia;
    }

    public Set<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Set<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public Set<GrupoInvestigador> getGrupoInvestigadores() {
        return grupoInvestigadores;
    }

    public void setGrupoInvestigadores(Set<GrupoInvestigador> grupoInvestigadores) {
        this.grupoInvestigadores = grupoInvestigadores;
    }
}

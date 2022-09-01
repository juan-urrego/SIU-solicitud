package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(
        name = "proyectos",
        uniqueConstraints = {
                @UniqueConstraint(name = "pr_codigo_proyecto_unique", columnNames = "pr_codigo_proyecto")
        }
)
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_id")
    private int id;
    @Column(name = "pr_nombre", nullable = false)
    private String nombre;
    @Column(name = "pr_codigo_proyecto", nullable = false)
    private String codigoProyecto;
    @Column(name = "pr_centro_costos", nullable = false)
    private String centroCostos;

    @JsonIgnore
    @OneToMany(mappedBy = "proyecto")
    Set<GrupoInvestigador> grupoInvestigadores;

    @ManyToOne
    @JsonIgnoreProperties("proyectos")
    @JoinColumn(name = "pr_grupo_id")
    private Grupo grupo;

    public Proyecto() {
    }

    public Proyecto(String nombre, String codigoProyecto, String centroCostos, Grupo grupo) {
        this.nombre = nombre;
        this.codigoProyecto = codigoProyecto;
        this.centroCostos = centroCostos;
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

    public String getCentroCostos() {
        return centroCostos;
    }

    public void setCentroCostos(String centoCostos) {
        this.centroCostos = centoCostos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Set<GrupoInvestigador> getGrupoInvestigadores() {
        return grupoInvestigadores;
    }

    public void setGrupoInvestigadores(Set<GrupoInvestigador> grupoInvestigadores) {
        this.grupoInvestigadores = grupoInvestigadores;
    }
}

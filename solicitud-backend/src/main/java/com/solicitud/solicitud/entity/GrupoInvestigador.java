package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "grupo_investigadores")
public class GrupoInvestigador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gi_id")
    private int id;
    @Column(name = "gi_cargo")
    @NotNull
    private String cargo;
    @NotNull
    @Column(name = "gi_nombre_contacto")
    private String nomreContacto;
    @NotNull
    @Column(name = "gi_telefono_contacto")
    private String telefonoContacto;

    @ManyToOne
    @JoinColumn(name = "gi_proyecto_id")
    private Proyecto proyecto;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "gi_grupo_id")
    private Grupo grupo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "gi_investigador_id")
    private Investigador investigador;

    @JsonIgnore
    @OneToOne(mappedBy = "grupoInvestigador")
    private Solicitud solicitud;

    public GrupoInvestigador(@NotNull String cargo, @NotNull String nombreContacto, @NotNull String telefonoContacto, @NotNull Grupo grupo, @NotNull Investigador investigador, Proyecto proyecto) {
        this.cargo = cargo;
        this.nomreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
        this.grupo = grupo;
        this.investigador = investigador;
        this.proyecto = proyecto;
    }

    public GrupoInvestigador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitudes(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public String getNomreContacto() {
        return nomreContacto;
    }

    public void setNomreContacto(String nomreContacto) {
        this.nomreContacto = nomreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}

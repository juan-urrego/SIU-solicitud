package com.solicitud.solicitud.entity;

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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "gi_grupo_id")
    private Grupo grupo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "gi_investigador_id")
    private Investigador investigador;

    @OneToMany(mappedBy = "grupoInvestigador")
    private Set<Solicitud> solicitudes;

    public GrupoInvestigador(@NotNull String cargo, @NotNull String nombreContacto, @NotNull String telefonoContacto, @NotNull Grupo grupo, @NotNull Investigador investigador) {
        this.cargo = cargo;
        this.nomreContacto = nombreContacto;
        this.telefonoContacto = telefonoContacto;
        this.grupo = grupo;
        this.investigador = investigador;
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

    public Set<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(Set<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
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
}

package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solicitud.solicitud.security.entity.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "estudios")
public class Estudio {

    @Id
    @Column(name = "est_id")
    private int id;

    @NotNull
    @Column(name = "est_acuerdo")
    private String acuerdo;

    @NotNull
    @Column(name = "est_firma_usuario")
    private byte firmaUsuario;

    @NotNull
    @Column(name = "est_firma_investigador")
    private byte firmaInvestigador;

    @OneToOne
    @MapsId
    @JoinColumn(name = "est_id")
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "est_unidad_academica_id")
    private UnidadAcademica unidadAcademica;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "est_estado_id")
    private Estado estado;

    @ManyToOne
    @JsonIgnoreProperties({"password","roles"})
    @JoinColumn(name = "est_usuario_id")
    private Usuario usuario;

    public Estudio() {
    }

    public Estudio(@NotNull String acuerdo, @NotNull byte firmaUsuario, @NotNull byte firmaInvestigador, Solicitud solicitud, UnidadAcademica unidadAcademica, @NotNull Estado estado, Usuario usuario) {
        this.acuerdo = acuerdo;
        this.firmaUsuario = firmaUsuario;
        this.firmaInvestigador = firmaInvestigador;
        this.solicitud = solicitud;
        this.unidadAcademica = unidadAcademica;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public byte getFirmaUsuario() {
        return firmaUsuario;
    }

    public void setFirmaUsuario(byte firmaUsuario) {
        this.firmaUsuario = firmaUsuario;
    }

    public byte getFirmaInvestigador() {
        return firmaInvestigador;
    }

    public void setFirmaInvestigador(byte firmaInvestigador) {
        this.firmaInvestigador = firmaInvestigador;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public UnidadAcademica getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(UnidadAcademica unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

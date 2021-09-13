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


    @Column(name = "est_firma_usuario")
    private String firmaUsuario;

    @Column(name = "est_firma_director")
    private String firmaDirector;

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
    
    public Estudio() {
    }

    public Estudio(@NotNull String acuerdo, @NotNull String firmaUsuario, @NotNull String firmaDirector, Solicitud solicitud, UnidadAcademica unidadAcademica, @NotNull Estado estado) {
        this.acuerdo = acuerdo;
        this.firmaUsuario = firmaUsuario;
        this.firmaDirector = firmaDirector;
        this.solicitud = solicitud;
        this.unidadAcademica = unidadAcademica;
        this.estado = estado;
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

    public String getFirmaUsuario() {
        return firmaUsuario;
    }

    public void setFirmaUsuario(String firmaUsuario) {
        this.firmaUsuario = firmaUsuario;
    }

    public String getFirmaDirector() {
        return firmaDirector;
    }

    public void setFirmaDirector(String firmaDirector) {
        this.firmaDirector = firmaDirector;
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
    
}

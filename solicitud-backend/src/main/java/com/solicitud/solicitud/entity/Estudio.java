package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solicitud.solicitud.security.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "estudios")
public class Estudio {

    @Id
    @Column(name = "est_id")
    private int id;

    @Column(name = "est_acuerdo", nullable = false)
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

    @ManyToOne
    @JsonIgnoreProperties({"password","roles"})
    @JoinColumn(name = "est_usuario_id")
    private User director;

    @ManyToOne
    @JoinColumn(name = "est_estado_id", nullable = false)
    private Estado estado;
    
    public Estudio() {
    }

    public Estudio(String acuerdo, Solicitud solicitud, User director, Estado estado) {
        this.acuerdo = acuerdo;
        this.solicitud = solicitud;
        this.director = director;
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

    public User getDirector() {
        return director;
    }

    public void setDirector(User director) {
        this.director = director;
    }
}

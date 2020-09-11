package com.solicitud.solicitud.entity;

import javax.persistence.*;

import com.solicitud.solicitud.enums.Estado;

@Entity
@Table(name = "estudios")
public class Estudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firma;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String unidad;

    @OneToOne
    private Director director;
    private String acuerdo;

    @OneToOne
    @JoinColumn(name = "solicitud_id_solicitud")
    private Solicitud solicitud;

    public Estudio(String firma, Estado estado, String unidad, Director director, String acuerdo, Solicitud solicitud) {
        this.firma = firma;
        this.estado = estado;
        this.unidad = unidad;
        this.director = director;
        this.acuerdo = acuerdo;
        this.solicitud = solicitud;
    }

    public Estudio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
    

}

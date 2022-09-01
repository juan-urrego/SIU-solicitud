package com.solicitud.solicitud.entity;


import javax.persistence.*;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @Column(name = "con_sol_id")
    private int id;
    @Column(name = "con_parametro")
    private String parametro;
    @OneToOne
    @MapsId
    @JoinColumn(name = "con_sol_id")
    private Solicitud solicitud;
    @ManyToOne
    @JoinColumn(name = "con_est_id", nullable = false)
    private Estado estado;

    public Consulta() {
    }

    public Consulta(String parametro, Solicitud solicitud, Estado estado) {
        this.parametro = parametro;
        this.solicitud = solicitud;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}

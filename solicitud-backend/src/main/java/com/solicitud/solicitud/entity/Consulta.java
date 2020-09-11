package com.solicitud.solicitud.entity;


import javax.persistence.*;

import com.solicitud.solicitud.enums.Estado;


@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String acuerdo;
    private String porque;
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToOne
    private Precotizacion precotizacion;

    @OneToOne
    @JoinColumn(name = "solicitud_id_solicitud")
    private Solicitud solicitud;

    public Consulta(String acuerdo, String porque, Estado estado, Precotizacion precotizacion, Solicitud solicitud) {
        this.acuerdo = acuerdo;
        this.porque = porque;
        this.estado = estado;
        this.precotizacion = precotizacion;
        this.solicitud = solicitud;
    }

    public Consulta() {
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

    public String getPorque() {
        return porque;
    }

    public void setPorque(String porque) {
        this.porque = porque;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Precotizacion getPrecotizacion() {
        return precotizacion;
    }

    public void setPrecotizacion(Precotizacion precotizacion) {
        this.precotizacion = precotizacion;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
    
}

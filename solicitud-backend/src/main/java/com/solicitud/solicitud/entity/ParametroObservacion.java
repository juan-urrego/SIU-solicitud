package com.solicitud.solicitud.entity;


import javax.persistence.*;

@Entity
@Table(name = "ma_parametros_obeservacion")
public class ParametroObservacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "par_obs_id")
    private int id;
    @Column(name = "par_obs_descipcion", nullable = false)
    private String descripcion;
    @Column(name = "par_obs_parametro", nullable = false)
    private byte parametro;

    public ParametroObservacion(String descripcion, byte parametro) {
        this.descripcion = descripcion;
        this.parametro = parametro;
    }

    public ParametroObservacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte getParametro() {
        return parametro;
    }

    public void setParametro(byte parametro) {
        this.parametro = parametro;
    }
}

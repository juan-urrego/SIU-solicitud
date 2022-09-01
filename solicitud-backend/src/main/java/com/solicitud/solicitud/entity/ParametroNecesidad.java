package com.solicitud.solicitud.entity;

import javax.persistence.*;

@Entity
@Table(name = "ma_parametros_necesidad")
public class ParametroNecesidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parn_id")
    private int id;
    @Column(name = "parn_descipcion", nullable = false)
    private String descripcion;
    @Column(name = "parn_parametro", nullable = false)
    private byte parametro;

    public ParametroNecesidad(String descripcion, byte parametro) {
        this.descripcion = descripcion;
        this.parametro = parametro;
    }

    public ParametroNecesidad() {
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

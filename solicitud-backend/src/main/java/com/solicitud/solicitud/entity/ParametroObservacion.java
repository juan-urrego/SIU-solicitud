package com.solicitud.solicitud.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ma_parametros_obeservacion")
public class ParametroObservacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parObs_id")
    private int id;
    @NotNull
    @Column(name = "parObs_descipcion")
    private String descripcion;
    @NotNull
    @Column(name = "parObs_parametro")
    private byte parametro;

    public ParametroObservacion(@NotNull String descripcion, @NotNull byte parametro) {
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

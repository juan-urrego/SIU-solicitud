package com.solicitud.solicitud.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "ma_parametros_acuerdo")
public class ParametroAcuerdo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parAcu_id")
    private int id;
    @NotNull
    @Column(name = "parAcu_descipcion")
    private String descripcion;
    @NotNull
    @Column(name = "parAcu_parametro")
    private byte parametro;



    public ParametroAcuerdo(@NotNull String descripcion, @NotNull byte parametro) {
        this.descripcion = descripcion;
        this.parametro = parametro;
    }

    public ParametroAcuerdo() {
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

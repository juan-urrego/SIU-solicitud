package com.solicitud.solicitud.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ma_parametros_consulta")
public class ParametroConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parCon_id")
    private int id;
    @NotNull
    @Column(name = "parCon_descipcion")
    private String descripcion;
    @NotNull
    @Column(name = "parCon_parametro")
    private byte parametro;

    public ParametroConsulta(@NotNull String descripcion, @NotNull byte parametro) {
        this.descripcion = descripcion;
        this.parametro = parametro;
    }

    public ParametroConsulta() {
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

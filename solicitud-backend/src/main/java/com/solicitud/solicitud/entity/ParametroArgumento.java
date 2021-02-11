package com.solicitud.solicitud.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ma_parametros_argumento")
public class ParametroArgumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parArgs_id")
    private int id;
    @NotNull
    @Column(name = "parArgs_descipcion")
    private String descripcion;

    public ParametroArgumento(@NotNull String descripcion) {
        this.descripcion = descripcion;
    }

    public ParametroArgumento() {
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

}

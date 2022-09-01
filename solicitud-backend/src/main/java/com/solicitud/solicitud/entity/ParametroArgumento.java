package com.solicitud.solicitud.entity;

import javax.persistence.*;

@Entity
@Table(name = "ma_parametros_argumento")
public class ParametroArgumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "par_args_id")
    private int id;
    @Column(name = "par_args_descipcion", nullable = false)
    private String descripcion;

    public ParametroArgumento(String descripcion) {
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

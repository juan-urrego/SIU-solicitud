package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "argumentos")
public class Argumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "args_id")
    private int id;
    @NotNull
    @Column(name = "args_descripcion")
    private String descripcion;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "args_precotizacion_elegida")
    private Precotizacion precotizacion;

    public Argumento() {
    }

    public Argumento(@NotNull String descripcion, Precotizacion precotizacion) {
        this.descripcion = descripcion;
        this.precotizacion = precotizacion;
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

    public Precotizacion getPrecotizacion() {
        return precotizacion;
    }

    public void setPrecotizacion(Precotizacion precotizacion) {
        this.precotizacion = precotizacion;
    }
}

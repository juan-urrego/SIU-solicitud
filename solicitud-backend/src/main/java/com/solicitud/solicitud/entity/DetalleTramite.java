package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalles_tramites")
public class DetalleTramite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "det_id")
    private int id;

    @NotNull
    @Column(name = "det_descripcion")
    private String descripcion;

    @NotNull
    @Column(name = "det_cantidad")
    private int cantidad;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "det_solicitud_id")
    private Solicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "det_linea_producto_id")
    private LineaProducto lineaProducto;

    public DetalleTramite() {
    }

    public DetalleTramite(@NotNull String descripcion, @NotNull int cantidad, LineaProducto lineaProducto, Solicitud solicitud) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.lineaProducto = lineaProducto;
        this.solicitud = solicitud;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public LineaProducto getLineaProducto() {
        return lineaProducto;
    }

    public void setLineaProducto(LineaProducto lineaProducto) {
        this.lineaProducto = lineaProducto;
    }
}

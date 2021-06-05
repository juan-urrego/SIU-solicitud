package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

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
    @JsonIgnoreProperties({"detalleTramites"})
    @JoinColumn(name = "det_general_id")
    private LineaGeneral lineaGeneral;

    @ManyToOne
    @JoinColumn(name = "det_especifica_id")
    private LineaEspecifica lineaEspecifica;

    @JsonIgnore
    @OneToMany(mappedBy = "detalleTramite", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ProveedorDetalle> proveedorDetalles;

    public DetalleTramite() {
    }

    public DetalleTramite(@NotNull String descripcion, @NotNull int cantidad, LineaGeneral lineaGeneral, Solicitud solicitud, LineaEspecifica lineaEspecifica) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.lineaGeneral = lineaGeneral;
        this.solicitud = solicitud;
        this.lineaEspecifica = lineaEspecifica;
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

    public LineaGeneral getLineaGeneral() {
        return lineaGeneral;
    }

    public void setLineaGeneral(LineaGeneral lineaGeneral) {
        this.lineaGeneral = lineaGeneral;
    }

    public LineaEspecifica getLineaEspecifica() {
        return lineaEspecifica;
    }

    public void setLineaEspecifica(LineaEspecifica lineaEspecifica) {
        this.lineaEspecifica = lineaEspecifica;
    }

    public Set<ProveedorDetalle> getProveedorDetalles() {
        return proveedorDetalles;
    }

    public void setProveedorDetalles(Set<ProveedorDetalle> proveedorDetalles) {
        this.proveedorDetalles = proveedorDetalles;
    }

}

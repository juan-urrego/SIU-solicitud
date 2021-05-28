package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "proveedor_detalle")
public class ProveedorDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pd_id")
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pd_detalle_id")
    private DetalleTramite detalleTramite;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "gi_proveedor_id")
    private Proveedor proveedor;

    public ProveedorDetalle() {
    }

    public ProveedorDetalle(DetalleTramite detalleTramite, Proveedor proveedor) {
        this.detalleTramite = detalleTramite;
        this.proveedor = proveedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DetalleTramite getDetalleTramite() {
        return detalleTramite;
    }

    public void setDetalleTramite(DetalleTramite detalleTramite) {
        this.detalleTramite = detalleTramite;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}

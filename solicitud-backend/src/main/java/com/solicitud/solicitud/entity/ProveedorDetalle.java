package com.solicitud.solicitud.entity;

import javax.persistence.*;

@Entity
@Table(name = "proveedor_detalle")
public class ProveedorDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pd_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "pd_detalle_id", nullable = false)
    private DetalleTramite detalleTramite;

    @ManyToOne
    @JoinColumn(name = "gi_proveedor_id", nullable = false)
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

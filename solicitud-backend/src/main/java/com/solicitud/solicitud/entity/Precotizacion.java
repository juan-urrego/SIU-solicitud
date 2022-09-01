package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "precotizaciones")
public class Precotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pre_id")
    private int id;

    @Column(name = "pre_valor_total", nullable = false)
    private int valorTotal;
    @Column(name = "pre_valor_iva", nullable = false)
    private double valorIva;

    @ManyToOne
    @JoinColumn(name = "pre_pro_id")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "pre_sol_id")
    @JsonIgnore
    private Solicitud solicitud;

    @OneToOne(mappedBy = "precotizacionElegida")
    @JsonIgnore
    private Solicitud solicitudElegida;

    @OneToMany(mappedBy = "precotizacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Argumento> arguments;

    public Precotizacion(int valorTotal, int valorIva,  Proveedor proveedor) {
        this.valorTotal = valorTotal;
        this.valorIva = valorIva;
        this.proveedor = proveedor;
    }

    public Precotizacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorIva() {
        return valorIva;
    }

    public void setValorIva(double valorIva) {
        this.valorIva = valorIva;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Solicitud getSolicitudElegida() {
        return solicitudElegida;
    }

    public void setSolicitudElegida(Solicitud solicitudElegida) {
        this.solicitudElegida = solicitudElegida;
    }

    public Set<Argumento> getArgumentos() {
        return arguments;
    }

    public void setArgumentos(Set<Argumento> arguments) {
        this.arguments = arguments;
    }
}

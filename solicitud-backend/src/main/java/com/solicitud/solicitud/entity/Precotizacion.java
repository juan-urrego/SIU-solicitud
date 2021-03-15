package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "precotizaciones")
public class Precotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pre_id")
    private int id;

    @NotNull
    @Column(name = "pre_valor_total")
    private int valorTotal;
    @NotNull
    @Column(name = "pre_valor_iva")
    private int valorIva;

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

    @OneToMany(mappedBy = "precotizacion", cascade = CascadeType.ALL)
    private Set<Argumento> argumentos;

    public Precotizacion(@NotNull int valorTotal, @NotNull int valorIva, @NotNull Proveedor proveedor, Solicitud solicitud) {
        this.valorTotal = valorTotal;
        this.valorIva = valorIva;
        this.proveedor = proveedor;
        this.solicitud = solicitud;
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

    public int getValorIva() {
        return valorIva;
    }

    public void setValorIva(int valorIva) {
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
        return argumentos;
    }

    public void setArgumentos(Set<Argumento> argumentos) {
        this.argumentos = argumentos;
    }
}

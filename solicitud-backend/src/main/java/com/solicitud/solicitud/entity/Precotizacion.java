package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "precotizaciones")
public class Precotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precotizacion", insertable = false, updatable = false)
    private int idPrecotizacion;

    @ManyToOne
    @JsonIgnoreProperties({"precotizaciones"})
    @JoinColumn(name = "id_prov", nullable = false)
    private Proveedor proveedor;

//    @ManyToOne
//    @JsonIgnoreProperties({"precotizaciones","investigador","grupo"})
//    @JoinColumn(name = "id_soli" , nullable = false)
//    private Solicitud solicitud;
    private double valor;

    public Precotizacion(Proveedor proveedor, double valor) {
        this.proveedor = proveedor;
        this.valor = valor;
    }

    public Precotizacion() {
    }

    public int getIdPrecotizacion() {
        return idPrecotizacion;
    }

    public void setIdPrecotizacion(int idPrecotizacion) {
        this.idPrecotizacion = idPrecotizacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    


}

package com.solicitud.solicitud.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @Column(name = "id_proveedor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProveedor;
    private int nit;
    private String nombre;
    private int telefono;

    @JsonIgnoreProperties({"proveedor"})
    @OneToMany(mappedBy = "proveedor")
    private List<Precotizacion> precotizaciones;

    public Proveedor(int nit, String nombre, int telefono, List<Precotizacion> precotizaciones) {
        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.precotizaciones = precotizaciones;
    }

    public Proveedor() {
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public List<Precotizacion> getPrecotizaciones() {
        return precotizaciones;
    }

    public void setPrecotizaciones(List<Precotizacion> precotizaciones) {
        this.precotizaciones = precotizaciones;
    }
    
}

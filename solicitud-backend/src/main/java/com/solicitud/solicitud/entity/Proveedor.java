package com.solicitud.solicitud.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @Column(name = "pro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "pro_nombre", nullable = false)
    private String nombre;
    @Column(name = "pro_identificacion", nullable = false)
    private String identificacion;
    @Column(name = "pro_telefono", nullable = false)
    private String telefono;
    @Column(name = "pro_ciudad", nullable = false)
    private String ciudad;
    @Column(name = "pro_tipo", nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "proveedor")
    @JsonIgnore
    private Set<Precotizacion> precotizaciones;

    @JsonIgnore
    @OneToMany(mappedBy = "proveedor")
    Set<ProveedorDetalle> proveedorDetalles;

    public Proveedor(String nombre, String identificacion, String telefono, String ciudad, String tipo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.tipo = tipo;
    }

    public Proveedor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Set<Precotizacion> getPrecotizaciones() {
        return precotizaciones;
    }

    public void setPrecotizaciones(Set<Precotizacion> precotizaciones) {
        this.precotizaciones = precotizaciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<ProveedorDetalle> getProveedorDetalles() {
        return proveedorDetalles;
    }

    public void setProveedorDetalles(Set<ProveedorDetalle> proveedorDetalles) {
        this.proveedorDetalles = proveedorDetalles;
    }

}

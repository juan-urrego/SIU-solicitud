package com.solicitud.solicitud.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @Column(name = "pro_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(name = "pro_nombre")
    private String nombre;
    @NotNull
    @Column(name = "pro_identificacion")
    private String identificacion;
    @NotNull
    @Column(name = "pro_telefono")
    private String telefono;
    @NotNull
    @Column(name = "pro_ciudad")
    private String ciudad;
    @NotNull
    @Column(name = "pro_tipo")
    private String tipo;

    @OneToMany(mappedBy = "proveedor")
    @JsonIgnore
    private Set<Precotizacion> precotizaciones;

    @JsonIgnore
    @OneToMany(mappedBy = "proveedor")
    Set<ProveedorDetalle> proveedorDetalles;

    public Proveedor(@NotNull String nombre, @NotNull String identificacion, @NotNull String telefono, @NotNull String ciudad, @NotNull String tipo) {
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

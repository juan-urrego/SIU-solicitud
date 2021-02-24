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
    @Column(name = "pro_nit")
    private String nit;
    @NotNull
    @Column(name = "pro_telefono")
    private String telefono;
    @NotNull
    @Column(name = "pro_ciudad")
    private String ciudad;

    @OneToMany(mappedBy = "proveedor")
    @JsonIgnore
    private Set<Precotizacion> precotizaciones;

    public Proveedor(@NotNull String nombre, @NotNull String nit, @NotNull String telefono, @NotNull String ciudad) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
        this.ciudad = ciudad;
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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
}

package com.solicitud.solicitud.dto;

import javax.validation.constraints.NotBlank;

public class ProveedorDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String nit;
    @NotBlank
    private String telefono;
    @NotBlank
    private String ciudad;


    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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
}

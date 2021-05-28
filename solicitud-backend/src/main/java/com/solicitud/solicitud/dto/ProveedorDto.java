package com.solicitud.solicitud.dto;

import javax.validation.constraints.NotBlank;

public class ProveedorDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String identificacion;
    @NotBlank
    private String telefono;
    @NotBlank
    private String ciudad;
    @NotBlank
    private String tipo;


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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

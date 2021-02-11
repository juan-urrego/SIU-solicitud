package com.solicitud.solicitud.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class InvestigadorDto {

    @NotBlank
    private String  identificacion;
    @NotBlank
    private String nombre;
    @NotBlank
    private String telefono;
    @Email
    private String email;
    @NotBlank
    private String firma;

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

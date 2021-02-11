package com.solicitud.solicitud.dto;

import javax.validation.constraints.NotBlank;

public class GrupoDto {
    @NotBlank
    private String nombre;
    @NotBlank
    private String codColciencia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodColciencia() {
        return codColciencia;
    }

    public void setCodColciencia(String codColciencia) {
        this.codColciencia = codColciencia;
    }
}

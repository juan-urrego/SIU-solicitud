package com.solicitud.solicitud.dto;

import javax.validation.constraints.NotBlank;

public class LineaEspecificaDto {

    @NotBlank
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

package com.solicitud.solicitud.dto;

import javax.validation.constraints.NotBlank;

public class ParametroDto {

    @NotBlank
    private String descripcion;


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}

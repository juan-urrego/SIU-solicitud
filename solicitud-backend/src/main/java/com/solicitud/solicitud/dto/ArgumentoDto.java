package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.Precotizacion;

import javax.validation.constraints.NotBlank;

public class ArgumentoDto {

    @NotBlank
    private String descripcion;
    @NotBlank
    private Precotizacion precotizacion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Precotizacion getPrecotizacion() {
        return precotizacion;
    }

    public void setPrecotizacion(Precotizacion precotizacion) {
        this.precotizacion = precotizacion;
    }
}

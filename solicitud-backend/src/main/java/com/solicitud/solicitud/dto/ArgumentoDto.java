package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.Precotizacion;

import javax.validation.constraints.NotBlank;

public class ArgumentoDto {

    @NotBlank
    private String descripcion;
    @NotBlank
    private PrecotizacionDto precotizacion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PrecotizacionDto getPrecotizacion() {
        return precotizacion;
    }

    public void setPrecotizacion(PrecotizacionDto precotizacion) {
        this.precotizacion = precotizacion;
    }
}

package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.Precotizacion;

import javax.validation.constraints.NotBlank;

public class ConsultaDto {

    @NotBlank
    private String parametro;

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
}

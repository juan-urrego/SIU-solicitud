package com.solicitud.solicitud.dto;

import javax.validation.constraints.NotBlank;

public class LineaProductoDto {

    @NotBlank
    private String lineaNombre;

    public String getLineaNombre() {
        return lineaNombre;
    }

    public void setLineaNombre(String lineaNombre) {
        this.lineaNombre = lineaNombre;
    }
}

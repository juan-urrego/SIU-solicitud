package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.LineaEspecifica;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class LineaGeneralDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private Set<LineaEspecifica> lineaEspecificas;

    public Set<LineaEspecifica> getLineaEspecificas() {
        return lineaEspecificas;
    }

    public void setLineaEspecificas(Set<LineaEspecifica> lineaEspecificas) {
        this.lineaEspecificas = lineaEspecificas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

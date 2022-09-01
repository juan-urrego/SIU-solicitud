package com.solicitud.solicitud.dto;

import javax.validation.constraints.NotBlank;

public class GrupoDto {

    @NotBlank
    private String codigoGrupo;
    @NotBlank
    private String nombre;
    @NotBlank
    private String codigoColciencia;

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoColciencia() {
        return codigoColciencia;
    }

    public void setCodigoColciencia(String codigoColciencia) {
        this.codigoColciencia = codigoColciencia;
    }
}

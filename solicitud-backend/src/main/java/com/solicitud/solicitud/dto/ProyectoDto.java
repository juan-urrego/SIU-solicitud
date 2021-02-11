package com.solicitud.solicitud.dto;

import javax.validation.constraints.NotBlank;

public class ProyectoDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String codigoProyecto;
    @NotBlank
    private String centroCostos;
    @NotBlank
    private int grupo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public String getCentroCostos() {
        return centroCostos;
    }

    public void setCentroCostos(String centroCostos) {
        this.centroCostos = centroCostos;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }
}

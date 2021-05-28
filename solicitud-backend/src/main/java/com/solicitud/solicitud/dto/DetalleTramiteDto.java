package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.LineaEspecifica;

import javax.validation.constraints.NotBlank;

public class DetalleTramiteDto {

    @NotBlank
    private String descripcion;
    @NotBlank
    private int cantidad;
    @NotBlank
    private int lineaGeneral;
    @NotBlank
    private int lineaEspecifica;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getLineaGeneral() {
        return lineaGeneral;
    }

    public void setLineaGeneral(int lineaGeneral) {
        this.lineaGeneral = lineaGeneral;
    }

    public int getLineaEspecifica() {
        return lineaEspecifica;
    }

    public void setLineaEspecifica(int lineaEspecifica) {
        this.lineaEspecifica = lineaEspecifica;
    }
}

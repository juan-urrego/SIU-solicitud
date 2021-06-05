package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.LineaEspecifica;
import com.solicitud.solicitud.entity.LineaGeneral;

import javax.validation.constraints.NotBlank;

public class DetalleTramiteDto {

    @NotBlank
    private String descripcion;
    @NotBlank
    private int cantidad;
    @NotBlank
    private LineaGeneral lineaGeneral;
    @NotBlank
    private LineaEspecifica lineaEspecifica;

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

    public LineaGeneral getLineaGeneral() {
        return lineaGeneral;
    }

    public void setLineaGeneral(LineaGeneral lineaGeneral) {
        this.lineaGeneral = lineaGeneral;
    }

    public LineaEspecifica getLineaEspecifica() {
        return lineaEspecifica;
    }

    public void setLineaEspecifica(LineaEspecifica lineaEspecifica) {
        this.lineaEspecifica = lineaEspecifica;
    }
}

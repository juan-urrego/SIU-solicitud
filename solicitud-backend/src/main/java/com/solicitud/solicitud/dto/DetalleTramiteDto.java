package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.LineaProducto;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotBlank;

public class DetalleTramiteDto {

    @NotBlank
    private String descripcion;
    @NotBlank
    private int cantidad;
    @NotBlank
    private LineaProducto lineaProducto;

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

    public LineaProducto getLineaProducto() {
        return lineaProducto;
    }

    public void setLineaProducto(LineaProducto lineaProducto) {
        this.lineaProducto = lineaProducto;
    }
}

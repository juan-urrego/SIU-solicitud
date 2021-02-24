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
    private int lineaProducto;

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

    public int getLineaProducto() {
        return lineaProducto;
    }

    public void setLineaProducto(int lineaProducto) {
        this.lineaProducto = lineaProducto;
    }
}

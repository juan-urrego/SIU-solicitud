package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.Proveedor;


import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

public class PrecotizacionDto {

    @NotBlank
    private int valorTotal;
    @NotBlank
    private int valorIva;
    private int proveedorId;
    private String nombreProveedor;
    private String nitProveedor;
    private String telefonoProveedor;
    private String ciudadProveedor;
    private String tipoIdentificacion;
    @NotBlank
    private Set<ArgumentoDto> argumentoDtos = new HashSet<>();

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getValorIva() {
        return valorIva;
    }

    public void setValorIva(int valorIva) {
        this.valorIva = valorIva;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getCiudadProveedor() {
        return ciudadProveedor;
    }

    public void setCiudadProveedor(String ciudadProveedor) {
        this.ciudadProveedor = ciudadProveedor;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Set<ArgumentoDto> getArgumentoDtos() {
        return argumentoDtos;
    }

    public void setArgumentoDtos(Set<ArgumentoDto> argumentoDtos) {
        this.argumentoDtos = argumentoDtos;
    }
}

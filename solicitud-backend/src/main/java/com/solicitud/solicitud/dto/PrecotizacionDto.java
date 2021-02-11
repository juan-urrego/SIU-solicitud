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
    @NotBlank
    private Proveedor proveedor;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Set<ArgumentoDto> getArgumentoDtos() {
        return argumentoDtos;
    }

    public void setArgumentoDtos(Set<ArgumentoDto> argumentoDtos) {
        this.argumentoDtos = argumentoDtos;
    }
}

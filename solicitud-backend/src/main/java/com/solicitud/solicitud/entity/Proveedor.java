package com.solicitud.solicitud.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @Column(name = "id_proveedor")
    private int idProveedor;
    private int nit;
    private String nombre;
    private int telefono;

    @JsonIgnoreProperties({"proveedor"})
    @OneToMany(mappedBy = "proveedor")
    private List<Precotizacion> precotizaciones;
}

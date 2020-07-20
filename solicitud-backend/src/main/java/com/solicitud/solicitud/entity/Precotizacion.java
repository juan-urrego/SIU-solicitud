package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "precotizaciones")
public class Precotizacion {

    @Id
    @GeneratedValue
    @Column(name = "id_precotizacion", insertable = false, updatable = false)
    private int idPrecotizacion;

    @ManyToOne
    @JsonIgnoreProperties({"precotizaciones"})
    @JoinColumn(name = "id_prov", nullable = false)
    private Proveedor proveedor;

//    @ManyToOne
//    @JsonIgnoreProperties({"precotizaciones","investigador","grupo"})
//    @JoinColumn(name = "id_soli" , nullable = false)
//    private Solicitud solicitud;
    private double valor;


}

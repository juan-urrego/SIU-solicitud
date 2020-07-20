package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sol_tramites")
public class Solicitud {

    @Id
    @GeneratedValue
    @Column(name = "id_solicitud")
    private int idSolicitud;
    private String necesidad;
    private String descripcion;
    private double valor;
    private String verificacion;
    private String observacion;
    private String cargo;
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    private String fecha;
    private String rubro;
    private String subrubro;
    private String financiador;
    @Column(name = "centro_costos")
    private String centroCostos;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_grup" , nullable = false)
    @JsonIgnoreProperties({"solicitudes","investigadores"})
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "id_inv", nullable = false)
    @JsonIgnoreProperties({"grupos", "solicitudes"})
    private Investigador investigador;

    @OneToMany(cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"proveedor"})
    @JoinTable(
            name = "solicitud_precotizacion",
            joinColumns = @JoinColumn(name = "id_solicitudes"),
            inverseJoinColumns = @JoinColumn(name = "id_precotizaciones")

    )
    private List<Precotizacion> precotizaciones;
}

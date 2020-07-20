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
@Table(name = "investigadores")
public class Investigador {

    @Id
    @Column(name = "id_investigador")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idInvestigador;
    private String identificacion;
    private String nombre;
    private double telefono;
    private String email;

    @OneToMany(mappedBy = "investigador")
    @JsonIgnoreProperties({"grupo","investigador","precotizaciones"})
    private List<Solicitud> solicitudes;

    @JsonIgnoreProperties({"solicitudes","investigadores"})
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
            name = "inv_grupos",
            joinColumns = @JoinColumn(name = "id_investigadores"),
            inverseJoinColumns = @JoinColumn(name = "id_grupos")
    )
    private List<Grupo> grupos;

}

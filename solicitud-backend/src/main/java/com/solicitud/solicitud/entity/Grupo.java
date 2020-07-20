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
@Table(name = "grupos")
public class Grupo {

    @Id
    @Column(name = "id_grupo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idGrupo;
    private String nombre;
    @Column(name = "codigo_col")
    private String codCol;


    @OneToMany(mappedBy = "grupo")
    @JsonIgnoreProperties({"grupo","investigador","precotizaciones"})
    private List<Solicitud> solicitudes;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
            name = "inv_grupos",
            joinColumns = @JoinColumn(name = "id_grupos"),
            inverseJoinColumns = @JoinColumn(name = "id_investigadores")
    )
    @JsonIgnoreProperties({"grupos","solicitudes"})
    private List<Investigador> investigadores;
}

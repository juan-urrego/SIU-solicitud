package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @Column(name = "id_grupo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Grupo(String nombre, String codCol, List<Solicitud> solicitudes, List<Investigador> investigadores) {
        this.nombre = nombre;
        this.codCol = codCol;
        this.solicitudes = solicitudes;
        this.investigadores = investigadores;
    }

    public Grupo() {
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodCol() {
        return codCol;
    }

    public void setCodCol(String codCol) {
        this.codCol = codCol;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<Investigador> getInvestigadores() {
        return investigadores;
    }

    public void setInvestigadores(List<Investigador> investigadores) {
        this.investigadores = investigadores;
    }

    
}

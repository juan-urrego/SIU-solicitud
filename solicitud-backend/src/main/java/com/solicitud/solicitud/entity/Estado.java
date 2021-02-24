package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solicitud.solicitud.enums.EstadoNombre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "ma_estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "es_id")
    private int id;
    @Column(name = "es_nombre")
    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoNombre estadoNombre;

    @OneToMany(mappedBy = "estado")
    @JsonIgnore
    private Set<Solicitud> solicitudes;

    @OneToMany(mappedBy = "estado")
    @JsonIgnore
    private Set<Estudio> estudios;

    @OneToMany(mappedBy = "estado")
    @JsonIgnore
    private Set<Consulta> consultas;

    public Estado(@NotNull EstadoNombre estadoNombre) {
        this.estadoNombre = estadoNombre;
    }

    public Estado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EstadoNombre getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(EstadoNombre estadoNombre) {
        this.estadoNombre = estadoNombre;
    }

    public Set<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(Set<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public Set<Estudio> getEstudios() {
        return estudios;
    }

    public void setEstudios(Set<Estudio> estudios) {
        this.estudios = estudios;
    }

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }
}

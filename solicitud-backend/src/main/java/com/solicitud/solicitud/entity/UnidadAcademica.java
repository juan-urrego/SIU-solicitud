package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "ma_unidad_academicas")
public class UnidadAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uni_id")
    private int id;
    @NotNull
    @Column(name = "uni_nombre")
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "unidadAcademica")
    private Set<Estudio> estudios;

    public UnidadAcademica(@NotNull String nombre) {
        this.nombre = nombre;
    }

    public UnidadAcademica() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Estudio> getEstudios() {
        return estudios;
    }

    public void setEstudios(Set<Estudio> estudios) {
        this.estudios = estudios;
    }
}

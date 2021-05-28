package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "unidadAcademica")
    private Set<Estudio> estudios;

    public UnidadAcademica(@NotNull String descripcion) {
        this.descripcion = descripcion;
    }

    public UnidadAcademica() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Estudio> getEstudios() {
        return estudios;
    }

    public void setEstudios(Set<Estudio> estudios) {
        this.estudios = estudios;
    }
}

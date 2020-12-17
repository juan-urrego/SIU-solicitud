package com.solicitud.solicitud.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ma_unidad_académica")
public class UnidadAcadémica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uni_id")
    private int uniId;
    @NotNull
    @Column(name = "uni_nombre")
    private String uniNombre;

    public UnidadAcadémica(@NotNull String uniNombre) {
        this.uniNombre = uniNombre;
    }

    public UnidadAcadémica() {
    }

    public int getUniId() {
        return uniId;
    }

    public void setUniId(int uniId) {
        this.uniId = uniId;
    }

    public String getUniNombre() {
        return uniNombre;
    }

    public void setUniNombre(String uniNombre) {
        this.uniNombre = uniNombre;
    }
}

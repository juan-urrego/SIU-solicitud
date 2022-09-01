package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "ma_lineas_generales")
public class LineaGeneral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_id")
    private int id;

    @NotNull
    @Column(name = "general_nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "lineaGeneral")
    @JsonIgnore
    private Set<DetalleTramite> detalleTramites;


    @OneToMany(mappedBy = "lineaGeneral",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties
    private Set<LineaEspecifica> lineaEspecificas;


    public LineaGeneral() {
    }

    public LineaGeneral(String nombre) {
        this.nombre = nombre;
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

    public Set<DetalleTramite> getDetalleTramites() {
        return detalleTramites;
    }

    public void setDetalleTramites(Set<DetalleTramite> detalleTramites) {
        this.detalleTramites = detalleTramites;
    }

    public Set<LineaEspecifica> getLineaEspecificas() {
        return lineaEspecificas;
    }

    public void setLineaEspecificas(Set<LineaEspecifica> lineaEspecificas) {
        if (this.lineaEspecificas == null) {
            this.lineaEspecificas = lineaEspecificas;
        }else {
            this.lineaEspecificas.clear();
            this.lineaEspecificas.addAll(lineaEspecificas);
        }
    }

    public LineaEspecifica getLineaEspecificaById(int idP) {
        return this.lineaEspecificas.stream()
                .filter(lineaEspecifica -> idP == lineaEspecifica.getId())
                .findAny()
                .orElse(null);
    }
}

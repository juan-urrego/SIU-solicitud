package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "ma_lineas_productos")
public class LineaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linea_id")
    private int id;

    @NotNull
    @Column(name = "linea_nombre")
    private String nombre;

    @OneToMany(mappedBy = "lineaProducto")
    @JsonIgnore
    private Set<DetalleTramite> detalleTramites;

    public LineaProducto() {
    }

    public LineaProducto(@NotNull String nombre) {
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
}

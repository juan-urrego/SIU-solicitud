package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ma_lineas_especificas")
public class LineaEspecifica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "espec_id")
    private int id;

    @Column(name = "espec_nombre")
    private String nombre;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "espec_general_id")
    private LineaGeneral lineaGeneral;

    @OneToMany(mappedBy = "lineaEspecifica")
    @JsonIgnore
    private Set<DetalleTramite> detalleTramites;


    public LineaEspecifica() {
    }

    public LineaEspecifica(String nombre, LineaGeneral lineaGeneral) {
        this.nombre = nombre;
        this.lineaGeneral = lineaGeneral;
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

    public LineaGeneral getLineaGeneral() {
        return lineaGeneral;
    }

    public void setLineaGeneral(LineaGeneral lineaGeneral) {
        this.lineaGeneral = lineaGeneral;
    }
}

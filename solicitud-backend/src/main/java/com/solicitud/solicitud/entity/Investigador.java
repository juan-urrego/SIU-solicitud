package com.solicitud.solicitud.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(
        name = "investigadores",
        uniqueConstraints = {
                @UniqueConstraint(name = "inv_identificacion_unique", columnNames = "inv_identificacion"),
                @UniqueConstraint(name = "inv_email_unique", columnNames = "inv_email")
        })
public class Investigador {

    @Id
    @Column(name = "inv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "inv_identificacion", nullable = false)
    private String identificacion;
    @Column(name = "inv_nombre", nullable = false)
    private String nombre;
    @Column(name = "inv_telefono", nullable = false)
    private String telefono;
    @Column(name = "inv_email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "investigador")
    @JsonIgnore
    private Set<GrupoInvestigador> grupoInvestigadores;

    public Investigador(String identificacion, String nombre, String telefono, String email) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public Investigador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<GrupoInvestigador> getGrupoInvestigadores() {
        return grupoInvestigadores;
    }

    public void setGrupoInvestigadores(Set<GrupoInvestigador> grupoInvestigadores) {
        this.grupoInvestigadores = grupoInvestigadores;
    }

}



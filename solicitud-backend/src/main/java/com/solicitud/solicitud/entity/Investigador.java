package com.solicitud.solicitud.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "investigadores")
public class Investigador {

    @Id
    @Column(name = "id_investigador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInvestigador;
    private String identificacion;
    private String nombre;
    private double telefono;
    private String email;

    @OneToMany(mappedBy = "investigador")
    @JsonIgnoreProperties({"grupo","investigador","precotizaciones"})
    private List<Solicitud> solicitudes;

    @JsonIgnoreProperties({"solicitudes","investigadores"})
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
            name = "inv_grupos",
            joinColumns = @JoinColumn(name = "id_investigadores"),
            inverseJoinColumns = @JoinColumn(name = "id_grupos")
    )
    private List<Grupo> grupos;

    public Investigador(String identificacion, String nombre, double telefono, String email,
            List<Solicitud> solicitudes, List<Grupo> grupos) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.solicitudes = solicitudes;
        this.grupos = grupos;
    }

    public Investigador() {
    }

    public int getIdInvestigador() {
        return idInvestigador;
    }

    public void setIdInvestigador(int idInvestigador) {
        this.idInvestigador = idInvestigador;
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

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }


    
}

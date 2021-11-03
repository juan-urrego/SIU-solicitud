package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solicitud.solicitud.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sol_id")
    private int id;
    @NotNull
    @Column(name = "sol_tipo_tramite")
    private String tipoTramite;
    @NotNull
    @Column(name = "sol_necesidad")
    private String necesidad;
    @NotNull
    @Column(name = "sol_fecha")
    private String fecha;
    @NotNull
    @Column(name = "sol_valor")
    private double valor;
    @NotNull
    @NotNull
    @Column(name = "sol_verificacion")
    private String verificacion;
    @NotNull
    @Column(name = "sol_observacion")
    private String observacion;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sol_grupo_investigador_id")
    private GrupoInvestigador grupoInvestigador;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sol_estado_id")
    private Estado estado;

    @OneToOne(mappedBy = "solicitud")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Estudio estudio;

    @OneToOne(mappedBy = "solicitud")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Consulta consulta;

    @JsonIgnoreProperties({"solicitud", "solicitudElegida", "argumentos"})
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Precotizacion> precotizaciones;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sol_precotizacion_elegida_id")
    private Precotizacion precotizacionElegida;

    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleTramite> detalleTramites;

    @ManyToOne
    @JsonIgnoreProperties({"password","roles"})
    @JoinColumn(name = "sol_usuario_id")
    private User user;

    public Solicitud(@NotNull String tipoTramite, @NotNull String necesidad, @NotNull String fecha, @NotNull double valor, @NotNull @NotNull String verificacion, @NotNull String observacion, @NotNull GrupoInvestigador grupoInvestigador, @NotNull Estado estado, @NotNull User user) {
        this.tipoTramite = tipoTramite;
        this.necesidad = necesidad;
        this.fecha = fecha;
        this.valor = valor;
        this.verificacion = verificacion;
        this.observacion = observacion;
        this.grupoInvestigador = grupoInvestigador;
        this.estado = estado;
        this.user = user;
    }

    public Solicitud() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public Set<DetalleTramite> getDetalleTramites() {
        return detalleTramites;
    }

    public void setDetalleTramites(Set<DetalleTramite> detalleTramites) {
        if (this.detalleTramites == null) {
            this.detalleTramites = detalleTramites;
        }else {
            this.detalleTramites.clear();
            this.detalleTramites.addAll(detalleTramites);
        }
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getNecesidad() {
        return necesidad;
    }

    public void setNecesidad(String necesidad) {
        this.necesidad = necesidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getVerificacion() {
        return verificacion;
    }

    public void setVerificacion(String verificacion) {
        this.verificacion = verificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public GrupoInvestigador getGrupoInvestigador() {
        return grupoInvestigador;
    }

    public void setGrupoInvestigador(GrupoInvestigador grupoInvestigador) {
        this.grupoInvestigador = grupoInvestigador;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Set<Precotizacion> getPrecotizaciones() {
        return precotizaciones;
    }

    public void setPrecotizaciones(Set<Precotizacion> precotizaciones) {
        if (this.precotizaciones == null) {
            this.precotizaciones = precotizaciones;
        }else {
            this.precotizaciones.clear();
            this.precotizaciones.addAll(precotizaciones);
        }
    }

    public Precotizacion getPrecotizacionElegida() {
        return precotizacionElegida;
    }

    public void setPrecotizacionElegida(Precotizacion precotizacionElegida) {
        this.precotizacionElegida = precotizacionElegida;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

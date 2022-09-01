package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solicitud.solicitud.security.entity.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sol_id")
    private int id;
    @Column(name = "sol_tipo_tramite", nullable = false)
    private String tipoTramite;
    @Column(name = "sol_necesidad", nullable = false)
    private String necesidad;
    @Column(name = "sol_fecha", nullable = false)
    private String fecha;
    @Column(name = "sol_valor", nullable = false)
    private double valor;
    @Column(name = "sol_verificacion", nullable = false)
    private String verificacion;
    @Column(name = "sol_observacion", nullable = false)
    private String observacion;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sol_grupo_investigador_id", nullable = false)
    private GrupoInvestigador grupoInvestigador;

    @ManyToOne
    @JoinColumn(name = "sol_estado_id", nullable = false)
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
    @JoinColumn(name = "sol_precotizacion_elegida_id", nullable = false)
    private Precotizacion precotizacionElegida;

    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleTramite> detalleTramites;

    @ManyToOne
    @JsonIgnoreProperties({"password","roles"})
    @JoinColumn(name = "sol_usuario_id", nullable = false)
    private User user;

    public Solicitud(String tipoTramite,
                     String necesidad,
                     String fecha,
                     double valor,
                     String verificacion,
                     String observacion,
                     GrupoInvestigador grupoInvestigador,
                     Estado estado,
                     User user) {
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

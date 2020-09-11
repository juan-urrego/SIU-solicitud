package com.solicitud.solicitud.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solicitud.solicitud.enums.Estado;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sol_tramites")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private int idSolicitud;
    private String necesidad;
    private String descripcion;
    private double valor;
    private String verificacion;
    private String observacion;
    private String cargo;
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    private String fecha;

    private String rubro;
    private String subrubro;
    private String financiador;
    @Column(name = "centro_costos")
    private String centroCostos;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_grup" , nullable = false)
    @JsonIgnoreProperties({"solicitudes","investigadores"})
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name = "id_inv", nullable = false)
    @JsonIgnoreProperties({"grupos", "solicitudes"})
    private Investigador investigador;

    @OneToMany(cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"proveedor"})
    @JoinTable(
            name = "solicitud_precotizacion",
            joinColumns = @JoinColumn(name = "id_solicitudes"),
            inverseJoinColumns = @JoinColumn(name = "id_precotizaciones")

    )
    private List<Precotizacion> precotizaciones;

    @JsonIgnoreProperties({"director","solicitud"})
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "solicitud")
    private Estudio estudio;

    @JsonIgnoreProperties({"director","solicitud"})
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "solicitud")
    private Consulta consulta;

    public Solicitud(String necesidad, String descripcion, double valor, String verificacion, String observacion,
            String cargo, String nombreProyecto, String fecha, String rubro, String subrubro, String financiador,
            String centroCostos, Estado estado, Grupo grupo, Investigador investigador,
            List<Precotizacion> precotizaciones, Estudio estudio, Consulta consulta) {
        this.necesidad = necesidad;
        this.descripcion = descripcion;
        this.valor = valor;
        this.verificacion = verificacion;
        this.observacion = observacion;
        this.cargo = cargo;
        this.nombreProyecto = nombreProyecto;
        this.fecha = fecha;
        this.rubro = rubro;
        this.subrubro = subrubro;
        this.financiador = financiador;
        this.centroCostos = centroCostos;
        this.estado = estado;
        this.grupo = grupo;
        this.investigador = investigador;
        this.precotizaciones = precotizaciones;
        this.estudio = estudio;
        this.consulta = consulta;
    }

    public Solicitud() {
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getNecesidad() {
        return necesidad;
    }

    public void setNecesidad(String necesidad) {
        this.necesidad = necesidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getSubrubro() {
        return subrubro;
    }

    public void setSubrubro(String subrubro) {
        this.subrubro = subrubro;
    }

    public String getFinanciador() {
        return financiador;
    }

    public void setFinanciador(String financiador) {
        this.financiador = financiador;
    }

    public String getCentroCostos() {
        return centroCostos;
    }

    public void setCentroCostos(String centroCostos) {
        this.centroCostos = centroCostos;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    public List<Precotizacion> getPrecotizaciones() {
        return precotizaciones;
    }

    public void setPrecotizaciones(List<Precotizacion> precotizaciones) {
        this.precotizaciones = precotizaciones;
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

    
}

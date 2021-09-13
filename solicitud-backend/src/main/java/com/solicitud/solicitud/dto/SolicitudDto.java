package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.*;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SolicitudDto {


    @NotBlank
    private Grupo grupo;
    @NotBlank
    private Proyecto proyecto;
    @NotBlank
    private Investigador investigador;
    @NotBlank
    private String cargo;
    @NotBlank
    private String nombreContacto;
    @NotBlank
    private String telefonoContacto;
    @NotBlank
    private String tipoTramite;
    @NotBlank
    private String necesidad;
    @NotBlank
    private String fecha;
    @NotBlank
    private int valor;
    @NotBlank
    private String verificacion;
    @NotBlank
    private String observacion;
    @NotBlank
    private Set<Precotizacion> precotizacionDtos = new HashSet<>();
    @NotBlank
    private Precotizacion precotizacionDto;
    @NotBlank
    private Set<DetalleTramite> detalleTramiteDtos = new HashSet<>();
    @NotBlank
    private Set<Argumento> argumentoDtos = new HashSet<>();
    @Email
    private String usuarioEmail;


    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Set<Argumento> getArgumentoDtos() {
        return argumentoDtos;
    }

    public void setArgumentoDtos(Set<Argumento> argumentoDtos) {
        this.argumentoDtos = argumentoDtos;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getTipoTramite() {
        return tipoTramite;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
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

    public Set<Precotizacion> getPrecotizacionDtos() {
        return precotizacionDtos;
    }

    public void setPrecotizacionDtos(Set<Precotizacion> precotizacionDtos) {
        this.precotizacionDtos = precotizacionDtos;
    }

    public Precotizacion getPrecotizacionDto() {
        return precotizacionDto;
    }

    public void setPrecotizacionDto(Precotizacion precotizacionDto) {
        this.precotizacionDto = precotizacionDto;
    }

    public Set<DetalleTramite> getDetalleTramiteDtos() {
        return detalleTramiteDtos;
    }

    public void setDetalleTramiteDtos(Set<DetalleTramite> detalleTramiteDtos) {
        this.detalleTramiteDtos = detalleTramiteDtos;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }
}

package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.DetalleTramite;
import com.solicitud.solicitud.entity.Grupo;
import com.solicitud.solicitud.entity.Investigador;
import com.solicitud.solicitud.entity.Precotizacion;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SolicitudDto {


    @NotBlank
    private int grupo;
    @NotBlank
    private int investigador;
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
    private Date fecha;
    @NotBlank
    private int valor;
    @NotBlank
    private String verificacion;
    @NotBlank
    private String observacion;
    @NotBlank
    private Set<PrecotizacionDto> precotizacionDtos = new HashSet<>();
    @NotBlank
    private PrecotizacionDto precotizacionDto;
    @NotBlank
    private Set<DetalleTramiteDto> detalleTramiteDtos = new HashSet<>();
    @NotBlank
    private Set<ArgumentoDto> argumentoDtos = new HashSet<>();

    public Set<ArgumentoDto> getArgumentoDtos() {
        return argumentoDtos;
    }

    public void setArgumentoDtos(Set<ArgumentoDto> argumentoDtos) {
        this.argumentoDtos = argumentoDtos;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getInvestigador() {
        return investigador;
    }

    public void setInvestigador(int investigador) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    public Set<PrecotizacionDto> getPrecotizacionDtos() {
        return precotizacionDtos;
    }

    public void setPrecotizacionDtos(Set<PrecotizacionDto> precotizacionDtos) {
        this.precotizacionDtos = precotizacionDtos;
    }

    public PrecotizacionDto getPrecotizacionDto() {
        return precotizacionDto;
    }

    public void setPrecotizacionDto(PrecotizacionDto precotizacionDto) {
        this.precotizacionDto = precotizacionDto;
    }

    public Set<DetalleTramiteDto> getDetalleTramiteDtos() {
        return detalleTramiteDtos;
    }

    public void setDetalleTramiteDtos(Set<DetalleTramiteDto> detalleTramiteDtos) {
        this.detalleTramiteDtos = detalleTramiteDtos;
    }
}

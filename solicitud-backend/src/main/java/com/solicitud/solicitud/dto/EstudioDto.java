package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.UnidadAcademica;
import com.solicitud.solicitud.security.entity.Usuario;

import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;

public class EstudioDto {

    @NotBlank
    private UnidadAcademica unidadAcademica;
    @NotBlank
    private String acuerdo;
    @NotBlank
    private String firmaUsuario;
    @NotBlank
    private String firmaDirector;

    public UnidadAcademica getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(UnidadAcademica unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public String getFirmaUsuario() {
        return firmaUsuario;
    }

    public void setFirmaUsuario(String firmaUsuario) {
        this.firmaUsuario = firmaUsuario;
    }

    public String getFirmaDirector() {
        return firmaDirector;
    }

    public void setFirmaDirector(String firmaDirector) {
        this.firmaDirector = firmaDirector;
    }
}

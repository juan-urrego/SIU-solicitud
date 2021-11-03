package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.UnidadAcademica;
import com.solicitud.solicitud.security.entity.User;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class EstudioDto {

    @NotBlank
    private UnidadAcademica unidadAcademica;
    private boolean firmaUsuario;
    private boolean firmaDirector;

    public UnidadAcademica getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(UnidadAcademica unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }

    public boolean isFirmaUsuario() {
        return firmaUsuario;
    }

    public void setFirmaUsuario(boolean firmaUsuario) {
        this.firmaUsuario = firmaUsuario;
    }

    public boolean isFirmaDirector() {
        return firmaDirector;
    }

    public void setFirmaDirector(boolean firmaDirector) {
        this.firmaDirector = firmaDirector;
    }
}

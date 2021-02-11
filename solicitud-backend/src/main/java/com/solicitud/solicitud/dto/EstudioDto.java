package com.solicitud.solicitud.dto;

import com.solicitud.solicitud.entity.UnidadAcademica;
import com.solicitud.solicitud.security.entity.Usuario;

import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;

public class EstudioDto {

    @NotBlank
    private int unidadAcademica;
    @NotBlank
    private String acuerdo;
    @NotBlank
    private byte firmaUsuario;
    @NotBlank
    private byte firmaInvestigador;

    public int getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(int unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public byte getFirmaUsuario() {
        return firmaUsuario;
    }

    public void setFirmaUsuario(byte firmaUsuario) {
        this.firmaUsuario = firmaUsuario;
    }

    public byte getFirmaInvestigador() {
        return firmaInvestigador;
    }

    public void setFirmaInvestigador(byte firmaInvestigador) {
        this.firmaInvestigador = firmaInvestigador;
    }
}

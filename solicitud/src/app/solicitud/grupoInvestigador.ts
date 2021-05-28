import { Investigador } from "../configuracion/investigador/investigador";
import { Grupo } from "../configuracion/grupo/grupo";

export interface GrupoInvestigador {
    id ?: number,
    cargo : string,
    nombreContacto : string,
    telefonoContacto : string,
    grupo : Grupo,
    investigador : Investigador
}
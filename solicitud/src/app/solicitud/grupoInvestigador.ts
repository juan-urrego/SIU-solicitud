import { Investigador } from "../configuracion/investigador/investigador";
import { Grupo } from "../configuracion/grupo/grupo";
import { Proyecto } from "../configuracion/proyecto/proyecto";

export interface GrupoInvestigador {
    id ?: number,
    cargo : string,
    nombreContacto : string,
    telefonoContacto : string,
    grupo : Grupo,
    investigador : Investigador,
    proyecto: Proyecto
}
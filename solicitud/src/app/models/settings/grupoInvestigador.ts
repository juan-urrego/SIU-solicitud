import { Investigador } from "./investigador";
import { Grupo } from "./grupo";

export interface GrupoInvestigador {
    id ?: number,
    cargo : string,
    nombreContacto : string,
    telefonoContacto : string,
    grupo : Grupo,
    investigador : Investigador
}
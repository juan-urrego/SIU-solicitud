import { Solicitud } from './solicitud';
import { Investigador } from './investigador';

export interface Grupo {
    idGrupo: number,
    nombre: string,
    codCol: string,
    solicitudes ?: Solicitud[]
    investigadores ?: Investigador[]
}
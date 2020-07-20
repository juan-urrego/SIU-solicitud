import { Solicitud } from './solicitud';
import { Grupo } from './grupo';

export interface Investigador {
    idInvestigador: number,
    identificacion: string,
    nombre: string,
    telefono: number,
    email: number,
    solicitudes ?: Solicitud[],
    grupos ?: Grupo[]
}
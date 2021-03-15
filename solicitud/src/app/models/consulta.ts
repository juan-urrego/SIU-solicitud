import { Estado } from './settings/estado';
import { Solicitud } from './solicitud';

export interface Consulta {
    id ?: number,
    parametro ?: string,
    solicitud : Solicitud,
    estado : Estado
}
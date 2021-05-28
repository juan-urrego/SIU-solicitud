import { Estado } from '../solicitud/estado';
import { Solicitud } from '../solicitud/solicitud';

export interface Consulta {
    id ?: number,
    parametro ?: string,
    solicitud : Solicitud,
    estado : Estado
}
import { Precotizacion } from './precotizacion';
import { Solicitud } from './solicitud';

export interface Consulta {
    id: number,
    acuerdo?: string,
    porque?: string,
    estado?: string,
    precotizacion ?: Precotizacion,
    solicitud ?: Solicitud
}
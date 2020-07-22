import { Precotizacion } from './precotizacion';
import { Solicitud } from './solicitud';

export interface Consulta {
    id: number,
    porque: string,
    estado: string,
    precotizacion ?: Precotizacion,
    solicitud ?: Solicitud
}
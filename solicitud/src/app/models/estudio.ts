
import { Solicitud } from './solicitud';
import { Director } from './director';

export interface Estudio {
    id:number,
    unidad?: string,
    firma?: string,
    estado?: string,
    acuerdo?: string,
    director ?: Director,
    solicitud ?: Solicitud

}

import { Solicitud } from './solicitud';
import { Director } from './director';

export interface Estudio {
    id:number,
    firma: string,
    estado: string,
    acuerdo: string,
    director ?: Director,
    solicitud ?: Solicitud

}
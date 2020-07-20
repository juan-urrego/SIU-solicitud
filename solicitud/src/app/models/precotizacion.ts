import { Proveedor } from './proveedor';
import { Solicitud } from './solicitud';

export interface Precotizacion {
    idPrecotizacion: number,
    valor: number,
    proveedor ?: Proveedor,
    solicitud ?: Solicitud
}
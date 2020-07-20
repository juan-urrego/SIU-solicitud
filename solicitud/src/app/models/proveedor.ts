import { Precotizacion } from './precotizacion';

export interface Proveedor {
    idProveedor: number,
    nit: number,
    nombre: string,
    telefono: number,
    precotizaciones ?: Precotizacion[]
}
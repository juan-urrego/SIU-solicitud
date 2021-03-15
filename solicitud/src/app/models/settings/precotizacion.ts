import { Proveedor } from './proveedor';
import { Argumento } from './argumento';

export interface Precotizacion {
    id ?: number,
    valorTotal: number,
    valorIva: number,
    proveedor : Proveedor,
    argumentos : Argumento[]
}
import { Investigador } from './investigador';
import { Grupo } from './grupo';
import { Precotizacion } from './precotizacion';

export interface Solicitud {
    idSolicitud: number,
    grupo ?: Grupo,
    investigador ?: Investigador,
    necesidad: string,
    descripcion: string,
    valor: number,
    verificacion: string,
    observacion: string,
    cargo: string,
    nombreProyecto: string,
    fecha: string,
    rubro: string,
    subrubro: string,
    financiador: string,
    centroCostos: string,
    precotizaciones ?: Precotizacion[],
    estado: string
}

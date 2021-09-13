import { LineaGeneral } from '../configuracion/linea-general/lineaGeneral';
import { lineaEspecifica } from '../configuracion/linea-general/lineaEspecifica';
import { Grupo } from '../configuracion/grupo/grupo';
import { Investigador } from '../configuracion/investigador/investigador';
import { Proyecto } from '../configuracion/proyecto/proyecto';
import { Proveedor } from '../configuracion/proveedor/proveedor';
import { FormGroup } from '@angular/forms';


export interface Solicitud {
    id ?: number,
    tipoTramite : string,
    necesidad : string,
    fecha : string,
    valor : number,
    verificacion : number,
    observacion : string,
    grupoInvestigador : GrupoInvestigador,
    estado : Estado,
    precotizaciones : Precotizacion[],
    precotizacionElegida : Precotizacion,
    detalleTramites : DetalleTramite[],
    usuario: any
}

export interface Argumento {
    id ?: number,
    descripcion : string,
    precotizacion : Precotizacion
}

export interface DetalleTramite {
    id ?: number,
    lineaGeneral : LineaGeneral,
    lineaEspecifica: lineaEspecifica
    descripcion : string,
    cantidad : number
}

export interface GrupoInvestigador {
    id ?: number,
    cargo : string,
    nombreContacto : string,
    telefonoContacto : string,
    grupo : Grupo,
    investigador : Investigador,
    proyecto: Proyecto
}

export interface Precotizacion {
    id ?: number,
    valorTotal: number,
    valorIva: number,
    proveedor : Proveedor,
    argumentos : Argumento[]
}

export interface SolicitudResolved {
    solicitud: Solicitud;
    error ?: any;
    form : FormGroup
}

export interface Estado {
    id ?: number,
    estadoNombre : string
}

import { Precotizacion } from './precotizacion';
import { GrupoInvestigador } from './grupoInvestigador';
import { Estado } from './estado';
import { DetalleTramite } from './detalleTramite';

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
    detalleTramites : DetalleTramite[]
}

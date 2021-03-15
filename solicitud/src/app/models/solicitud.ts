import { Precotizacion } from './settings/precotizacion';
import { GrupoInvestigador } from './settings/grupoInvestigador';
import { Estado } from './settings/estado';
import { DetalleTramite } from './settings/detalleTramite';

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
    detalleTramites : DetalleTramite
}

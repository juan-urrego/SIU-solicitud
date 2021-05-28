import { Estado } from '../solicitud/estado';
import { Parametro } from '../configuracion/parametro/parametro';
import { Solicitud } from '../solicitud/solicitud';

export interface Estudio {
    id ?: number,
    acuerdo ?: string,
    firmaUsuario ?: number,
    firmaInvestigador ?: number,
    solicitud : Solicitud
    unidadAcademica : Parametro,
    estado : Estado
}
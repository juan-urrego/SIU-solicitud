import { Estado } from './settings/estado';
import { UnidadAcademica } from './settings/unidadAcademica';
import { Solicitud } from './solicitud';

export interface Estudio {
    id ?: number,
    acuerdo ?: string,
    firmaUsuario ?: number,
    firmaInvestigador ?: number,
    solicitud : Solicitud
    unidadAcademica : UnidadAcademica,
    estado : Estado
}
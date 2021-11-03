import { FormGroup } from '@angular/forms';
import { Parametro } from '../configuracion/parametro/parametro';
import { Estado, Solicitud } from '../solicitud/solicitud';
import { User } from '../user/user';

export interface Estudio {
    id ?: number,
    acuerdo ?: string,
    director: User,
    firmaUsuario ?: string,
    firmaDirector ?: string,
    solicitud : Solicitud
    unidadAcademica : Parametro,
    estado : Estado
}

export interface EstudiioResolved {
    estudio: Estudio;
    error ?: any;
    form : FormGroup
}
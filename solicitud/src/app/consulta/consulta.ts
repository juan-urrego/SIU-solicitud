import { FormGroup } from '@angular/forms';
import { Solicitud, Estado } from '../solicitud/solicitud';

export interface Consulta {
    id ?: number,
    parametro ?: string,
    solicitud : Solicitud,
    estado : Estado
}

export interface ConsultaResolved {
    consulta: Consulta;
    error ?: any;
    form : FormGroup
}
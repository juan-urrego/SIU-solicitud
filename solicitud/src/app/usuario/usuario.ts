import { FormGroup } from "@angular/forms";

export interface Usuario {
    id ?: number,
    nombre: string,
    apellido: string,
    email: string,
    password: string,
    cargo: string,
    roles: any,
    firma: string
}

export interface UsuarioResolved {
    usuario: Usuario;
    error ?: any;
    form : FormGroup
}
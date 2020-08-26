export class NuevoUsuario {
    id ?: number;
    nombre: string;
    apellido: string;
    email: string;
    password: string;
    authorities: string[];
}
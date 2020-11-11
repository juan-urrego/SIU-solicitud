export class NuevoUsuario {
    id ?: number;
    nombre: string;
    apellido: string;
    email: string;
    password: string;
    
    constructor(nombre: string, apellido: string, email: string, password: string){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email
        this.password = password;
    }
}
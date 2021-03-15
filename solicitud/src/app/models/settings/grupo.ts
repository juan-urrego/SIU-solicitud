import { Proyecto } from "./proyecto";

export interface Grupo {
    id ?: number,
    nombre: string,
    codColciencia : string,
    proyectos ?: Proyecto[]
}
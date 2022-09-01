import { Proyecto } from "../proyecto/proyecto";

export interface Grupo {
    id ?: number,
    codigoGrupo: string,
    nombre: string,
    codigoColciencia : string,
    proyectos ?: Proyecto[]
}
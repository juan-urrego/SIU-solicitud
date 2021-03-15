import { Grupo } from "./grupo";

export interface Proyecto {
    id ?: number,
    nombre : string,
    codigoProyecto : string,
    centroCostos : string,
    grupo : Grupo
}
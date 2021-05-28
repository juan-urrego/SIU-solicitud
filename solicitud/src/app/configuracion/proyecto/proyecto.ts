import { Grupo } from "../grupo/grupo";

export interface Proyecto {
    id ?: number,
    nombre : string,
    codigoProyecto : string,
    centroCostos : string,
    grupo : Grupo
}
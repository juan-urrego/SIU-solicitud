import { lineaEspecifica } from "./lineaEspecifica";

export interface LineaGeneral {
    id ?: number,
    nombre : string,
    lineaEspecificas: lineaEspecifica[]
}
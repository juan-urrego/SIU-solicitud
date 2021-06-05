import { lineaEspecifica } from "../configuracion/linea-general/lineaEspecifica";
import { LineaGeneral } from "../configuracion/linea-general/lineaGeneral";

export interface DetalleTramite {
    id ?: number,
    lineaGeneral : LineaGeneral,
    lineaEspecifica: lineaEspecifica
    descripcion : string,
    cantidad : number
}
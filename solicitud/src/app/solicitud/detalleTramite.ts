import { LineaGeneral } from "../configuracion/linea-general/lineaGeneral";

export interface DetalleTramite {
    id ?: number,
    descripcion : string,
    cantidad : number,
    lineaProducto : LineaGeneral
}
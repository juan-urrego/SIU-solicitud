import { LineaProducto } from "./lineaProducto";

export interface DetalleTramite {
    id ?: number,
    descripcion : string,
    cantidad : number,
    lineaProducto : LineaProducto
}
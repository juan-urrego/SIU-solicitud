import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LineaProducto } from '../../models/settings/lineaProducto';

@Injectable({providedIn: 'root'})
export class LineaProductoService {
    private url = 'http://localhost:9191/lineaProducto';

    constructor(private http: HttpClient) { }

    getLineaProductos(): Observable<LineaProducto[]> {
        return this.http.get<LineaProducto[]>(`${this.url}/lineasProductos`);
    }

    getLineaProducto(id: number): Observable<LineaProducto> {
        return this.http.get<LineaProducto>(`${this.url}/${id}`);
    }


    createLineaProducto(lineaProducto: LineaProducto): Observable<LineaProducto> {
        return this.http.post<LineaProducto>(`${this.url}/save`, lineaProducto);
    }

    deleteLineaProducto(id: number): Observable<{}> {
        return this.http.delete<LineaProducto>(`${this.url}/delete/${id}`);
    }

    updateLineaProducto(lineaProducto: LineaProducto): Observable<LineaProducto> {
        return this.http.put<LineaProducto>(`${this.url}/update/${lineaProducto.id}`, lineaProducto);
    }
}
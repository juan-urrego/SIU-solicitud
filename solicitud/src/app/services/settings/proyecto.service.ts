import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Proyecto } from '../../models/settings/proyecto';

@Injectable({ providedIn: 'root' })

export class ProyectoService {
    private url = 'http://localhost:9191/proyecto';

    constructor(private http: HttpClient) { }

    getProyectos(): Observable<Proyecto[]> {
        return this.http.get<Proyecto[]>(`${this.url}/proyectos`);
    }

    getProyecto(id: number): Observable<Proyecto> {
        return this.http.get<Proyecto>(`${this.url}/${id}`)
    }

    createProyecto(proyecto: Proyecto): Observable<Proyecto> {
        return this.http.post<Proyecto>(`${this.url}/save`, proyecto);
    }

    deleteProyecto(id: number): Observable<{}> {
        return this.http.delete<Proyecto>(`${this.url}/delete/${id}`);
    }

    updateProyecto(proyecto: Proyecto): Observable<Proyecto> {
        return this.http.put<Proyecto>(`${this.url}/update/${proyecto.id}`, proyecto);
    }
}
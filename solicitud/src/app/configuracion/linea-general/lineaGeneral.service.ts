import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LineaGeneral } from './lineaGeneral';

@Injectable({providedIn: 'root'})
export class LineaGeneralService {
    private url = 'http://localhost:9191/lineaGeneral';

    constructor(private http: HttpClient) { }

    getLineaGenerales(): Observable<LineaGeneral[]> {
        return this.http.get<LineaGeneral[]>(`${this.url}/lineasGenerales`);
    }

    getLineaGeneral(id: number): Observable<LineaGeneral> {
        return this.http.get<LineaGeneral>(`${this.url}/${id}`);
    }


    createLineaGeneral(lineaGeneral: LineaGeneral): Observable<LineaGeneral> {
        return this.http.post<LineaGeneral>(`${this.url}/save`, lineaGeneral);
    }

    deleteLineaGeneral(id: number): Observable<{}> {
        return this.http.delete<LineaGeneral>(`${this.url}/delete/${id}`);
    }

    updateLineaGeneral(lineaGeneral: LineaGeneral): Observable<LineaGeneral> {
        return this.http.put<LineaGeneral>(`${this.url}/update/${lineaGeneral.id}`, lineaGeneral);
    }
}
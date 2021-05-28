import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Parametro } from '../parametro';

@Injectable({providedIn: 'root'})
export class ParametroObservacionService {
    private url = 'http://localhost:9191/parametro/observacion';

    constructor(private http: HttpClient) { }

    getParametroObservaciones(): Observable<Parametro[]> {
        return this.http.get<Parametro[]>(`${this.url}/observaciones`);
    }

    getParametroObservacion(id: number): Observable<Parametro> {
        return this.http.get<Parametro>(`${this.url}/${id}`);
    }

    getParametroObservacionActivo(): Observable<Parametro> {
        return this.http.get<Parametro>(`${this.url}/observaciones/selected`);
    }


    createParametroObservacion(parametro: Parametro): Observable<Parametro> {
        return this.http.post<Parametro>(`${this.url}/save`, parametro);
    }

    deleteParametroObservacion(id: number): Observable<{}> {
        return this.http.delete<Parametro>(`${this.url}/delete/${id}`);
    }

    updateParametroObservacion(parametro: Parametro): Observable<Parametro> {
        return this.http.put<Parametro>(`${this.url}/update/${parametro.id}`, parametro);
    }

    updateParametroObservacionActivo(id: number): Observable<Parametro> {
        return this.http.put<Parametro>(`${this.url}/update/selected/${id}`, null);
    }
}
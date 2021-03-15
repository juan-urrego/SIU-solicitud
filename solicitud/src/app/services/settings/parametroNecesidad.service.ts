import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Parametro } from '../../models/settings/parametro';

@Injectable({providedIn: 'root'})
export class ParametroNecesidadService {
    private url = 'http://localhost:9191/parametro/necesidad';

    constructor(private http: HttpClient) { }

    getParametroNecesidades(): Observable<Parametro[]> {
        return this.http.get<Parametro[]>(`${this.url}/necesidades`);
    }

    getParametroNecesidad(id: number): Observable<Parametro> {
        return this.http.get<Parametro>(`${this.url}/${id}`);
    }

    getParametroNecesidadActivo(): Observable<Parametro> {
        return this.http.get<Parametro>(`${this.url}/necesidades/selected`);
    }


    createParametroNecesidad(parametro: Parametro): Observable<Parametro> {
        return this.http.post<Parametro>(`${this.url}/save`, parametro);
    }

    deleteParametroNecesidad(id: number): Observable<{}> {
        return this.http.delete<Parametro>(`${this.url}/delete/${id}`);
    }

    updateParametroNecesidad(parametro: Parametro): Observable<Parametro> {
        return this.http.put<Parametro>(`${this.url}/update/${parametro.id}`, parametro);
    }

    updateParametroNecesidadActivo(id: number): Observable<Parametro> {
        return this.http.put<Parametro>(`${this.url}/update/selected/${id}`, null);
    }
}
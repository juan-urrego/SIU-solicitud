import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Parametro } from '../parametro';

@Injectable({providedIn: 'root'})
export class ParametroConsultaService {
    private url = 'http://localhost:9191/parametro/consulta';

    constructor(private http: HttpClient) { }

    getParametroConsultas(): Observable<Parametro[]> {
        return this.http.get<Parametro[]>(`${this.url}/consultas`);
    }

    getParametroConsulta(id: number): Observable<Parametro> {
        return this.http.get<Parametro>(`${this.url}/${id}`);
    }

    getParametroConsultaActivo(): Observable<Parametro> {
        return this.http.get<Parametro>(`${this.url}/consultas/selected`);
    }


    createParametroConsulta(parametro: Parametro): Observable<Parametro> {
        return this.http.post<Parametro>(`${this.url}/save`, parametro);
    }

    deleteParametroConsulta(id: number): Observable<{}> {
        return this.http.delete<Parametro>(`${this.url}/delete/${id}`);
    }

    updateParametroConsulta(parametro: Parametro): Observable<Parametro> {
        return this.http.put<Parametro>(`${this.url}/update/${parametro.id}`, parametro);
    }

    updateParametroConsultaActivo(id: number): Observable<Parametro> {
        return this.http.put<Parametro>(`${this.url}/update/selected/${id}`, null);
    }
}
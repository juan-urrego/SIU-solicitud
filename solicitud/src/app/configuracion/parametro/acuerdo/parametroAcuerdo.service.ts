import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Parametro } from '../parametro';

@Injectable({providedIn: 'root'})
export class ParametroAcuerdoService {
    private url = 'http://localhost:9191/parametro/acuerdo';

    constructor(private http: HttpClient) { }

    getParametroAcuerdos(): Observable<Parametro[]> {
        return this.http.get<Parametro[]>(`${this.url}/acuerdos`);
    }

    getParametroAcuerdo(id: number): Observable<Parametro> {
        return this.http.get<Parametro>(`${this.url}/${id}`);
    }

    getParametroAcuerdoActivo(): Observable<Parametro> {
        return this.http.get<Parametro>(`${this.url}/acuerdos/selected`);
    }


    createParametroAcuerdo(parametro: Parametro): Observable<Parametro> {
        return this.http.post<Parametro>(`${this.url}/save`, parametro);
    }

    deleteParametroAcuerdo(id: number): Observable<{}> {
        return this.http.delete<Parametro>(`${this.url}/delete/${id}`);
    }

    updateParametroAcuerdo(parametro: Parametro): Observable<Parametro> {
        return this.http.put<Parametro>(`${this.url}/update/${parametro.id}`, parametro);
    }

    updateParametroAcuerdoActivo(id: number): Observable<Parametro> {
        return this.http.put<Parametro>(`${this.url}/update/selected/${id}`, null);
    }
}
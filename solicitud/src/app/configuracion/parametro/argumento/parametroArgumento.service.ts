import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Parametro } from '../parametro';

@Injectable({providedIn: 'root'})
export class ParametroArgumentoService {
    private url = 'http://localhost:9191/parametro/argumento';

    constructor(private http: HttpClient) { }

    getParametroArgumentos(): Observable<Parametro[]> {
        return this.http.get<Parametro[]>(`${this.url}/argumentos`);
    }

    getParametroArgumento(id: number): Observable<Parametro> {
        return this.http.get<Parametro>(`${this.url}/${id}`);
    }


    createParametroArgumento(parametro: Parametro): Observable<Parametro> {
        return this.http.post<Parametro>(`${this.url}/save`, parametro);
    }

    deleteParametroArgumento(id: number): Observable<{}> {
        return this.http.delete<Parametro>(`${this.url}/delete/${id}`);
    }

    updateParametroArgumento(parametro: Parametro): Observable<Parametro> {
        return this.http.put<Parametro>(`${this.url}/update/${parametro.id}`, parametro);
    }
}
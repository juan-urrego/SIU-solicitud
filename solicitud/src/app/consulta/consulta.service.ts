import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Consulta } from './consulta';


@Injectable({ providedIn: 'root' })
export class ConsultaService {
    private consultaUrl = 'http://localhost:9191/consulta';

    constructor(private http: HttpClient) { }

    getConsultas(): Observable<Consulta[]> {
        return this.http.get<Consulta[]>(`${this.consultaUrl}/consultas`);
    }

    getConsulta(id: number): Observable<Consulta> {
        return this.http.get<Consulta>(`${this.consultaUrl}/${id}`);
    }


    confirmarConsulta(id: number): Observable<Consulta> {
        return this.http.post<Consulta>(`${this.consultaUrl}/confirmar/${id}`, null);
    }

}
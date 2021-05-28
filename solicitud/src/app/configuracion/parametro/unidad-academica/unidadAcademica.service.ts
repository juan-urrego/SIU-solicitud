import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Parametro } from '../parametro';

@Injectable({ providedIn: 'root' })

export class UnidadAcademicaService {
    private url = 'http://localhost:9191/unidadAcademica';

    constructor(private http: HttpClient) { }

    getUnidadAcademicas(): Observable<Parametro[]> {
        return this.http.get<Parametro[]>(`${this.url}/unidadAcademicas`);
    }

    getUnidadAcademica(id: number): Observable<Parametro> {
        return this.http.get<Parametro>(`${this.url}/${id}`)
    }

    createUnidadAcademica(unidadAcademica: Parametro): Observable<Parametro> {
        return this.http.post<Parametro>(`${this.url}/save`, unidadAcademica);
    }

    deleteUnidadAcademica(id: number): Observable<{}> {
        return this.http.delete<Parametro>(`${this.url}/delete/${id}`);
    }

    updateUnidadAcademica(unidadAcademica: Parametro): Observable<Parametro> {
        return this.http.put<Parametro>(`${this.url}/update/${unidadAcademica.id}`, unidadAcademica);
    }
}
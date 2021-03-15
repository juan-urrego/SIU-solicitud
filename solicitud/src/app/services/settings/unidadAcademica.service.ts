import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UnidadAcademica } from '../../models/settings/unidadAcademica';

@Injectable({ providedIn: 'root' })

export class UnidadAcademicaService {
    private url = 'http://localhost:9191/unidadAcademica';

    constructor(private http: HttpClient) { }

    getUnidadAcademicas(): Observable<UnidadAcademica[]> {
        return this.http.get<UnidadAcademica[]>(`${this.url}/unidadAcademicas`);
    }

    getUnidadAcademica(id: number): Observable<UnidadAcademica> {
        return this.http.get<UnidadAcademica>(`${this.url}/${id}`)
    }

    createUnidadAcademica(unidadAcademica: UnidadAcademica): Observable<UnidadAcademica> {
        return this.http.post<UnidadAcademica>(`${this.url}/save`, unidadAcademica);
    }

    deleteUnidadAcademica(id: number): Observable<{}> {
        return this.http.delete<UnidadAcademica>(`${this.url}/delete/${id}`);
    }

    updateUnidadAcademica(unidadAcademica: UnidadAcademica): Observable<UnidadAcademica> {
        return this.http.put<UnidadAcademica>(`${this.url}/update/${unidadAcademica.id}`, unidadAcademica);
    }
}
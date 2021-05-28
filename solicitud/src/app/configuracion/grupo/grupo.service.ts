import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Grupo } from './grupo';

@Injectable({providedIn: 'root'})
export class GrupoService {
    private grupoUrl = 'http://localhost:9191/grupo';

    constructor(private http: HttpClient) { }

    getGrupos(): Observable<Grupo[]> {
        return this.http.get<Grupo[]>(`${this.grupoUrl}/grupos`);
    }

    getGrupo(id: number): Observable<Grupo> {
        return this.http.get<Grupo>(`${this.grupoUrl}/${id}`)
    }

    createGrupo(grupo: Grupo): Observable<Grupo> {
        return this.http.post<Grupo>(`${this.grupoUrl}/save`, grupo);
    }

    deleteGrupo(id: number): Observable<{}> {
        return this.http.delete<Grupo>(`${this.grupoUrl}/delete/${id}`);
    }

    updateGrupo(grupo: Grupo): Observable<Grupo> {
        return this.http.put<Grupo>(`${this.grupoUrl}/update/${grupo.id}`, grupo);
    }
}
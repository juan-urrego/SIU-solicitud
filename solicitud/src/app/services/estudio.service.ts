import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Estudio } from '../models/estudio';


@Injectable({providedIn: 'root'})
export class EstudioService {
    private estudioUrl = 'http://localhost:9191/estudio';

    constructor(private http: HttpClient) { }

    getEstudios(): Observable<Estudio[]> {
        return this.http.get<Estudio[]>(`${this.estudioUrl}/estudios`);
    }

    getEstudio(id: number): Observable<Estudio> {
        return this.http.get<Estudio>(`${this.estudioUrl}/${id}`);
    }


    updateEstudio(estudio: Estudio): Observable<Estudio> {
        return this.http.put<Estudio>(`${this.estudioUrl}/update/${estudio.id}`, estudio);
    }

    confirmarEstudio(id: number): Observable<Estudio> {
        return this.http.post<Estudio>(`${this.estudioUrl}/confirmar/${id}`, null);
    }

}
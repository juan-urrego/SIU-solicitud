import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Investigador } from './investigador';

@Injectable({providedIn: 'root'})
export class InvestigadorService {
    private investigadorUrl = 'http://localhost:9191/investigador';

    constructor(private http: HttpClient) { }

    getInvestigadores(): Observable<Investigador[]> {
        return this.http.get<Investigador[]>(`${this.investigadorUrl}/investigadores`);
    }

    getInvestigador(id: number): Observable<Investigador> {
        return this.http.get<Investigador>(`${this.investigadorUrl}/${id}`);
    }

    getInvestigadorFirma(id: number): Observable<Blob>{
        return this.http.get(`${this.investigadorUrl}/image/${id}`, {responseType: 'blob'});
    }

    createInvestigador(investigador: string, file: File): Observable<Investigador> {
        var data = new FormData();
        data.append('imageFile', file);
        data.append('investigador', investigador);
        return this.http.post<Investigador>(`${this.investigadorUrl}/save`, data);
    }

    deleteInvestigador(id: number): Observable<{}> {
        return this.http.delete<Investigador>(`${this.investigadorUrl}/delete/${id}`);
    }

    updateInvestigador(investigador: string, file: File, id: number): Observable<Investigador> {
        var data = new FormData();
        data.append('imageFile', file);
        data.append('investigador', investigador);
        return this.http.put<Investigador>(`${this.investigadorUrl}/update/${id}`, data);
    }

}
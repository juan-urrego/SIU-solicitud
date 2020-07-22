import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { Consulta } from '../models/consulta';


@Injectable({providedIn: 'root'})
export class ConsultaService {
    private consultaUrl = 'http://localhost:9191/consultas';

    constructor(private http: HttpClient) { }

    getConsultas(): Observable<Consulta[]> {
        return this.http.get<Consulta[]>(this.consultaUrl)
            .pipe(
                tap(data => console.log(JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    getConsulta(id: number): Observable<Consulta> {
        const url = `${this.consultaUrl}/${id}`;
        return this.http.get<Consulta>(url)
            .pipe(
                tap(data => console.log('Obtener Consulta: ' + JSON.stringify(data))),
                catchError(this.handleError)
            )
    }

    createConsulta(consulta: Consulta): Observable<Consulta> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.consultaUrl}/agregar`;
        consulta.id = null;
        return this.http.post<Consulta>(url, consulta, { headers })
            .pipe(
                tap(data => console.log('crear Consulta: ' + JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    deleteConsulta(id: number): Observable<{}> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.consultaUrl}/eliminar/${id}`;
        return this.http.delete<Consulta>(url, { headers })
            .pipe(
                tap(data => console.log('eliminar Consulta: ' + id)),
                catchError(this.handleError)
            );
    }

    updateConsulta(consulta: Consulta): Observable<Consulta> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.consultaUrl}/actualizar/${consulta.id}`;
        return this.http.put<Consulta>(url, consulta, { headers })
            .pipe(
                tap(() => console.log('update Consulta: ' + consulta.id)),
                // Return the product on an update
                map(() => consulta),
                catchError(this.handleError)
            );
    }



    private handleError(err) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        let errorMessage: string;
        if (err.error instanceof ErrorEvent) {
            // A client-side or network error occurred. Handle it accordingly.
            errorMessage = `An error occurred: ${err.error.message}`;
        } else {
            // The backend returned an unsuccessful response code.
            // The response body may contain clues as to what went wrong,
            errorMessage = `Backend returned code ${err.status}: ${err.body.error}`;
        }
        console.error(err);
        return throwError(errorMessage);
    }

}
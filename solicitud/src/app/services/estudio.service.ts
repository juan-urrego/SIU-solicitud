import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { Estudio } from '../models/estudio';


@Injectable({providedIn: 'root'})
export class EstudioService {
    private estudioUrl = 'http://localhost:9191/estudios';

    constructor(private http: HttpClient) { }

    getEstudios(): Observable<Estudio[]> {
        return this.http.get<Estudio[]>(this.estudioUrl)
            .pipe(
                tap(data => console.log(JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    getEstudio(id: number): Observable<Estudio> {
        const url = `${this.estudioUrl}/${id}`;
        return this.http.get<Estudio>(url)
            .pipe(
                tap(data => console.log('Obtener Estudio: ' + JSON.stringify(data))),
                catchError(this.handleError)
            )
    }

    createEstudio(estudio: Estudio): Observable<Estudio> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.estudioUrl}/agregar`;
        estudio.id = null;
        return this.http.post<Estudio>(url, estudio, { headers })
            .pipe(
                tap(data => console.log('crear Estudio: ' + JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    deleteEstudio(id: number): Observable<{}> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.estudioUrl}/eliminar/${id}`;
        return this.http.delete<Estudio>(url, { headers })
            .pipe(
                tap(data => console.log('eliminar Estudio: ' + id)),
                catchError(this.handleError)
            );
    }

    updateEstudio(estudio: Estudio): Observable<Estudio> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.estudioUrl}/actualizar/${estudio.id}`;
        return this.http.put<Estudio>(url, estudio, { headers })
            .pipe(
                tap(() => console.log('update Estudio: ' + estudio.id)),
                // Return the product on an update
                map(() => estudio),
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
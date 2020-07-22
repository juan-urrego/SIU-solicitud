import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { Director } from '../models/director';


@Injectable({providedIn: 'root'})
export class DirectorService {
    private directorUrl = 'http://localhost:9191/directores';

    constructor(private http: HttpClient) { }

    getDirectores(): Observable<Director[]> {
        return this.http.get<Director[]>(this.directorUrl)
            .pipe(
                tap(data => console.log(JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    getDirector(id: number): Observable<Director> {
        const url = `${this.directorUrl}/${id}`;
        return this.http.get<Director>(url)
            .pipe(
                tap(data => console.log('Obtener Director: ' + JSON.stringify(data))),
                catchError(this.handleError)
            )
    }

    createDirector(director: Director): Observable<Director> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.directorUrl}/agregar`;
        director.id = null;
        return this.http.post<Director>(url, director, { headers })
            .pipe(
                tap(data => console.log('crear Director: ' + JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    deleteDirector(id: number): Observable<{}> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.directorUrl}/eliminar/${id}`;
        return this.http.delete<Director>(url, { headers })
            .pipe(
                tap(data => console.log('eliminar Director: ' + id)),
                catchError(this.handleError)
            );
    }

    updateDirector(director: Director): Observable<Director> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.directorUrl}/actualizar/${director.id}`;
        return this.http.put<Director>(url, director, { headers })
            .pipe(
                tap(() => console.log('update Director: ' + director.id)),
                // Return the product on an update
                map(() => director),
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
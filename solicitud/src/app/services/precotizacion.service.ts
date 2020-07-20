import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { Precotizacion } from '../models/precotizacion';

@Injectable({ providedIn: 'root' })
export class PrecotizacionService {
    private precotizacionUrl = 'http://localhost:9191/precotizaciones';

    constructor(private http: HttpClient) { }

    getPrecotizaciones(): Observable<Precotizacion[]> {
        return this.http.get<Precotizacion[]>(this.precotizacionUrl)
            .pipe(
                tap(data => console.log(JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    getPrecotizacion(id: number): Observable<Precotizacion> {
        const url = `${this.precotizacionUrl}/${id}`;
        return this.http.get<Precotizacion>(url)
            .pipe(
                tap(data => console.log('Obtener Precotizacion: ' + JSON.stringify(data))),
                catchError(this.handleError)
            )
    }

    createPrecotizacion(precotizacion: Precotizacion): Observable<Precotizacion> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.precotizacionUrl}/agregar`;
        precotizacion.idPrecotizacion = null;
        return this.http.post<Precotizacion>(url, precotizacion, { headers })
            .pipe(
                tap(data => console.log('crear Precotizacion: ' + JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    deletePrecotizacion(id: number): Observable<{}> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.precotizacionUrl}/eliminar/${id}`;
        return this.http.delete<Precotizacion>(url, { headers })
            .pipe(
                tap(data => console.log('eliminar Precotizacion: ' + id)),
                catchError(this.handleError)
            );
    }

    updatePrecotizacion(precotizacion: Precotizacion): Observable<Precotizacion> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.precotizacionUrl}/actualizar/${precotizacion.idPrecotizacion}`;
        return this.http.put<Precotizacion>(url, precotizacion, { headers })
            .pipe(
                tap(() => console.log('update Precotizacion: ' + precotizacion.idPrecotizacion)),
                // Return the product on an update
                map(() => precotizacion),
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
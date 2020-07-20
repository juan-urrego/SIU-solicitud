import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { Solicitud } from '../models/solicitud';


@Injectable({providedIn: 'root'})
export class SolicitudService {
    private solicitudUrl = 'http://localhost:9191/solicitudes';

    constructor(private http: HttpClient) { }

    getSolicitudes(): Observable<Solicitud[]> {
        return this.http.get<Solicitud[]>(this.solicitudUrl)
            .pipe(
                tap(data => console.log(JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    getSolicitud(id: number): Observable<Solicitud> {
        if (id === 0) {
            return of(this.initializeSolicitud());
        }
        const url = `${this.solicitudUrl}/${id}`;
        return this.http.get<Solicitud>(url)
            .pipe(
                tap(data => console.log('Obtener Solicitud: ' + JSON.stringify(data))),
                catchError(this.handleError)
            )
    }

    createSolicitud(solicitud: Solicitud): Observable<Solicitud> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.solicitudUrl}/agregar`;
        solicitud.idSolicitud = null;
        return this.http.post<Solicitud>(url, solicitud, { headers })
            .pipe(
                tap(data => console.log('crear Solicitud: ' + JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    deleteSolicitud(id: number): Observable<{}> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.solicitudUrl}/eliminar/${id}`;
        return this.http.delete<Solicitud>(url, { headers })
            .pipe(
                tap(data => console.log('eliminar Solicitud: ' + id)),
                catchError(this.handleError)
            );
    }

    updateSolicitud(solicitud: Solicitud): Observable<Solicitud> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.solicitudUrl}/actualizar/${solicitud.idSolicitud}`;
        return this.http.put<Solicitud>(url, solicitud, { headers })
            .pipe(
                tap(() => console.log('update Solicitud: ' + solicitud.idSolicitud)),
                // Return the product on an update
                map(() => solicitud),
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
    private initializeSolicitud(): Solicitud {
        // Return an initialized object
        return {
          idSolicitud: 0,
          grupo: null,
          investigador: null,          
          necesidad: null,
          descripcion: null,
          valor: null,
          verificacion: null,
          observacion: null,
          cargo: null,
          nombreProyecto: null,
          fecha: null,
          rubro: null,
          subrubro: null,
          financiador: null,
          centroCostos: null,
          estado: 'Creada'
        };
      }
}
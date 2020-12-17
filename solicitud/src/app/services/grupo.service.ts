import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { Grupo } from '../models/grupo';


@Injectable({providedIn: 'root'})
export class GrupoService {
    private grupoUrl = 'http://localhost:9191/grupo';
    // headers = new HttpHeaders({
    //     'Content-Type': 'application/json',
    //     'Access-Control-Allow-Origin': "*"
    // });

    constructor(private http: HttpClient) { }

    getGrupos(): Observable<Grupo[]> {
        const url = `${this.grupoUrl}/grupos`;
        return this.http.get<Grupo[]>(url)
            .pipe(
                tap(data => console.log(JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    getGrupo(id: number): Observable<Grupo> {
        if (id === 0) {
            return of(this.initializeGrupo());
        }
        const url = `${this.grupoUrl}/${id}`;
        return this.http.get<Grupo>(url)
            .pipe(
                tap(data => console.log('Obtener Grupo: ' + JSON.stringify(data))),
                catchError(this.handleError)
            )
    }

    createGrupo(grupo: Grupo): Observable<Grupo> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.grupoUrl}/save`;
        grupo.idGrupo = null;
        return this.http.post<Grupo>(url, grupo, { headers })
            .pipe(
                tap(data => console.log('crear Grupo: ' + JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    deleteGrupo(id: number): Observable<{}> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.grupoUrl}/delete/${id}`;
        return this.http.delete<Grupo>(url, { headers })
            .pipe(
                tap(data => console.log('eliminar Grupo: ' + id)),
                catchError(this.handleError)
            );
    }

    updateGrupo(grupo: Grupo): Observable<Grupo> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.grupoUrl}/update/${grupo.idGrupo}`;
        return this.http.put<Grupo>(url, grupo, { headers })
            .pipe(
                tap(() => console.log('update Grupo: ' + grupo.idGrupo)),
                // Return the product on an update
                map(() => grupo),
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
    private initializeGrupo(): Grupo {
        // Return an initialized object
        return {
          idGrupo: 1,
          nombre: null,
          codCol: null          
        };
      }
}
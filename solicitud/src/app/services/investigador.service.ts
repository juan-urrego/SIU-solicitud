
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { Investigador } from '../models/investigador';

@Injectable({providedIn: 'root'})
export class InvestigadorService {
    private investigadorUrl = 'http://localhost:9191/investigadores';

    constructor(private http: HttpClient) { }

    getInvestigadores(): Observable<Investigador[]> {
        return this.http.get<Investigador[]>(this.investigadorUrl)
            .pipe(
                tap(data => console.log(JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    getInvestigador(id: number): Observable<Investigador> {
        if (id === 0) {
            return of(this.initializeInvestigador());
        }
        const url = `${this.investigadorUrl}/${id}`;
        return this.http.get<Investigador>(url)
            .pipe(
                tap(data => console.log('Obtener Investigador: ' + JSON.stringify(data))),
                catchError(this.handleError)
            )
    }

    createInvestigador(investigador: Investigador): Observable<Investigador> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.investigadorUrl}/agregar`;
        investigador.idInvestigador = null;
        return this.http.post<Investigador>(url, investigador, { headers })
            .pipe(
                tap(data => console.log('crear Investigador: ' + JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    deleteInvestigador(id: number): Observable<{}> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.investigadorUrl}/eliminar/${id}`;
        return this.http.delete<Investigador>(url, { headers })
            .pipe(
                tap(data => console.log('eliminar Investigador: ' + id)),
                catchError(this.handleError)
            );
    }

    updateInvestigador(investigador: Investigador): Observable<Investigador> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.investigadorUrl}/actualizar/${investigador.idInvestigador}`;
        return this.http.put<Investigador>(url, investigador, { headers })
            .pipe(
                tap(() => console.log('update Investigador: ' + investigador.idInvestigador)),
                // Return the product on an update
                map(() => investigador),
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

    private initializeInvestigador(): Investigador {
        // Return an initialized object
        return {
          idInvestigador: 0,
          identificacion: null,
          nombre: null,
          telefono: null,
          email: null 
        };
      }
}
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { Proveedor } from '../models/proveedor';
import { catchError, tap, map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })

export class ProveedorService {
    private proveedorUrl = 'http://localhost:9191/proveedor';

    constructor(private http: HttpClient) { }

    getProveedores(): Observable<Proveedor[]> {
        const url = `${this.proveedorUrl}/proveedores`;
        return this.http.get<Proveedor[]>(url)
            .pipe(
                tap(data => console.log(JSON.stringify(data))),
                catchError(this.handleError)
            );
    }

    getProveedor(id: number): Observable<Proveedor> {
        if (id === 0) {
            return of(this.initializeProveedor());
        }
        const url = `${this.proveedorUrl}/${id}`;
        return this.http.get<Proveedor>(url)
            .pipe(
                tap(data => console.log('Obtener proovedor: ' + JSON.stringify(data))),
                catchError(this.handleError)
            )
    }

    createProveedor(proveedor: Proveedor): Observable<Proveedor> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.proveedorUrl}/save`;
        proveedor.idProveedor = null;
        return this.http.post<Proveedor>(url, proveedor, { headers })
            .pipe(
                tap(data => console.log('crear Proveedor: ' + JSON.stringify(data))),
                catchError(this.handleError)                
            );
    }

    deleteProveedor(id: number): Observable<{}> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.proveedorUrl}/delete/${id}`;
        return this.http.delete<Proveedor>(url, { headers })
            .pipe(
                tap(data => console.log('eliminar Proveedor: ' + id)),
                catchError(this.handleError)                
            );
    }

    updateProveedor(proveedor: Proveedor): Observable<Proveedor> {
        const headers = new HttpHeaders({ 'Content-type': 'application/json' });
        const url = `${this.proveedorUrl}/update/${proveedor.idProveedor}`;
        return this.http.put<Proveedor>(url, proveedor, { headers })
            .pipe(
                tap(() => console.log('update Proveedor: ' + proveedor.idProveedor)),
                // Return the product on an update
                map(() => proveedor),
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

    private initializeProveedor(): Proveedor {
        // Return an initialized object
        return {
          idProveedor: 0,
          nombre: null,
          nit: null,
          telefono: null,        
        };
      }
}
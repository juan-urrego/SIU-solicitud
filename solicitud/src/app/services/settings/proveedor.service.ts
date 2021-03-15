import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Proveedor } from '../../models/settings/proveedor';

@Injectable({ providedIn: 'root' })
export class ProveedorService {
    private url = 'http://localhost:9191/proveedor';

    constructor(private http: HttpClient) { }

    getProveedores(): Observable<Proveedor[]> {
        return this.http.get<Proveedor[]>(`${this.url}/proveedores`);
    }

    getProveedor(id: number): Observable<Proveedor> {
        return this.http.get<Proveedor>(`${this.url}/${id}`)
    }

    createProveedor(proveedor: Proveedor): Observable<Proveedor> {
        return this.http.post<Proveedor>(`${this.url}/save`, proveedor);
    }

    deleteProveedor(id: number): Observable<{}> {
        return this.http.delete<Proveedor>(`${this.url}/delete/${id}`);
    }

    updateProveedor(proveedor: Proveedor): Observable<Proveedor> {
        return this.http.put<Proveedor>(`${this.url}/update/${proveedor.id}`, proveedor);
    }
}
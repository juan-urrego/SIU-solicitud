import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NuevoUsuario } from 'src/app/shared/models/login/nuevo-usuario';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class UserService {

    authURL = 'http://localhost:9191/auth/';

    constructor(private httpClient: HttpClient) { }

    public nuevo(nuevoUsuario: NuevoUsuario): Observable<any> {
        return this.httpClient.post<any>(this.authURL + 'nuevo', nuevoUsuario);
    }

    public getAuxiliares(): Observable<NuevoUsuario[]> {
        return this.httpClient.get<NuevoUsuario[]>(this.authURL + 'auxiliares');
    }

    public getFirmaByEmail(): void {
        //...
    }

    // public getAuxiliar(id: number): Observable<NuevoUsuario> {
    //     return this.httpClient.get<NuevoUsuario>(this.authURL + `auxiliar/${id}`);
    // }

    public delete(id: number): Observable<any> {
        return this.httpClient.delete<any>(this.authURL + `delete/${id}`);
    }

    // public update(id: number, nuevoUsuario: NuevoUsuario): Observable<any> {
    //     return this.httpClient.put<any>(this.authURL + `update/${id}`, nuevoUsuario);
    // }
}
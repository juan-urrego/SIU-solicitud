import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NuevoUsuario } from 'src/app/models/login/nuevo-usuario';
import { Observable } from 'rxjs';
import { JwtDto } from 'src/app/models/login/jwt-dto';
import { LoginUsuario } from 'src/app/models/login/login-usuario';

@Injectable({
    providedIn: 'root'
})

export class AuthService {

    authURL = 'http://localhost:9191/auth/';

    constructor(private httpClient: HttpClient) { }

    public nuevo(nuevoUsuario: NuevoUsuario): Observable<any> {
        return this.httpClient.post<any>(this.authURL + 'nuevo', nuevoUsuario);
    }

    public login(loginUsuario: LoginUsuario): Observable<JwtDto> {
        return this.httpClient.post<JwtDto>(this.authURL + 'login', loginUsuario);
    }


    public getAuxiliares(): Observable<NuevoUsuario[]> {
        return this.httpClient.get<NuevoUsuario[]>(this.authURL + 'auxiliares');
    }

    public getAuxiliar(id: number): Observable<NuevoUsuario> {
        return this.httpClient.get<NuevoUsuario>(this.authURL + `auxiliar/${id}`);
    }

    public delete(id: number): Observable<any> {
        return this.httpClient.delete<any>(this.authURL + `delete/${id}`);
    }

    public update(id: number, nuevoUsuario: NuevoUsuario): Observable<any> {
        return this.httpClient.put<any>(this.authURL + `update/${id}`, nuevoUsuario);
    }
}
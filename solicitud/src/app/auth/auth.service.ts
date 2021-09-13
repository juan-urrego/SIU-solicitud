import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JwtDto } from 'src/app/shared/models/login/jwt-dto';
import { LoginUsuario } from 'src/app/shared/models/login/login-usuario';


const TOKEN_KEY = 'AuthToken';


@Injectable({
    providedIn: 'root'
})
export class AuthService {
    roles: Array<string> = [];


    constructor(
        private httpClient : HttpClient,
        private router: Router) {
    }

    public setToken(token: string): void {
        window.localStorage.removeItem(TOKEN_KEY);
        window.localStorage.setItem(TOKEN_KEY, token);
    }


    public getToken(): string {
        return localStorage.getItem(TOKEN_KEY);
    }

    public isLogged(): boolean {
        if(this.getToken()) {
            return true;
        }
        return false;
    }

    public getEmail(): string {
        if(!this.isLogged()){
            return null;
        }
        const token = this.getToken()
        const payload = token.split('.')[1]
        const payloadDecoded = atob(payload);
        const values = JSON.parse(payloadDecoded);
        const userName = values.sub;
        return userName;
    }

    public isAdmin(): boolean {
        if(!this.isLogged()){
            return false;
        }
        const token = this.getToken()
        const payload = token.split('.')[1]
        const payloadDecoded = atob(payload);
        const values = JSON.parse(payloadDecoded);
        const roles = values.roles;
        if(roles.indexOf('ROLE_ADMIN') < 0) {
            return false;
        }
        return true;
    }

    public getRole(): string {
        if(!this.isLogged()){
            return null;
        }
        const token = this.getToken()
        const payload = token.split('.')[1]
        const payloadDecoded = atob(payload);
        const values = JSON.parse(payloadDecoded);
        const roles = values.roles;
        if(roles.length == 1){
            return "usuario";
        }
        if(roles.indexOf('ROLE_ADMIN') < 0) {
            return "director";
        }
        return "admin";
    }


    public login(loginUsuario: LoginUsuario): Observable<JwtDto> {
        return this.httpClient.post<JwtDto>('http://localhost:9191/auth/login', loginUsuario);
    }

    public logOut(): void {
        window.localStorage.clear();
        this.router.navigate(['/login']);
    }
}
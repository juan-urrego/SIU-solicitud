import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JwtDto } from 'src/app/models/login/jwt-dto';
import { LoginUsuario } from 'src/app/models/login/login-usuario';


const TOKEN_KEY = 'AuthToken';
const EMAIL_KEY = 'AuthEmail';
const AUTHORITIES_KEY = 'AuthAuthorities';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private currentUserSubject: BehaviorSubject<JwtDto>;
    public currentUser: Observable<JwtDto>;
    roles: Array<string> = [];


    constructor(
        private httpClient : HttpClient
    ) {
        this.currentUserSubject = new BehaviorSubject<JwtDto>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();

    }

    public setToken(token: string): void {
        window.sessionStorage.removeItem(TOKEN_KEY);
        window.sessionStorage.setItem(TOKEN_KEY, token);
    }


    public getToken(): string {
        return sessionStorage.getItem(TOKEN_KEY);
    }

    public setEmail(email: string): void {
        window.sessionStorage.removeItem(EMAIL_KEY);
        window.sessionStorage.setItem(EMAIL_KEY, email);
    }

    public getEmail(): string {
        return sessionStorage.getItem(EMAIL_KEY);
    }
    public setAuthorities(authorities: string[]): void {
        window.sessionStorage.removeItem(AUTHORITIES_KEY);
        window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
    }

    public getAuthorities(): string[] {
        this.roles = [];
        if (sessionStorage.getItem(AUTHORITIES_KEY)) {
            JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)).forEach(authority => {
                this.roles.push(authority.authority)
            })
        }
        return this.roles;
    }

    public get currentUserValue(): JwtDto {
        return this.currentUserSubject.value;
    }

    public login(loginUsuario: LoginUsuario): Observable<JwtDto> {
        return this.httpClient.post<JwtDto>('http://localhost:9191/auth/login', loginUsuario)
            .pipe(map(user =>{
                this.setToken(user.token);
                this.setEmail(user.email);
                this.setAuthorities(user.authorities);
                this.currentUserSubject.next(user);
                return user;
            }));
    }

    public logOut(): void {
        window.sessionStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}
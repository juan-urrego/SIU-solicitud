import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';

@Injectable({
    providedIn: 'root'
})

export class UserService {

    authURL = 'http://localhost:9191/auth';

    constructor(private httpClient: HttpClient) { }

   
    public getUser(id: number): Observable<Usuario> {
        return this.httpClient.get<Usuario>(`${this.authURL}/auxiliar/${id}`);
    }

    public getUsers(): Observable<Usuario[]> {
        return this.httpClient.get<Usuario[]>(`${this.authURL}/auxiliares`);
    }

    public getUsuarioFirma(id: number): Observable<Blob> {
        return this.httpClient.get(`${this.authURL}/image/${id}`, {responseType: 'blob'});
    }

    public getDirectorActivo(): Observable<Usuario> {
        return this.httpClient.get<Usuario>(`${this.authURL}/directorActivo`);
    }

    public getUserByFirma(firma: string): Observable<Usuario> {
        return this.httpClient.get<Usuario>(`${this.authURL}/director/${firma}`);
    }
    
    public createUser(user: string, file: File): Observable<Usuario> {
        var data = new FormData();
        data.append('imageFile', file);
        data.append('usuario', user);
        return this.httpClient.post<Usuario>(`${this.authURL}/nuevo`, data);
    }

    public deleteUser(id: number): Observable<{}> {
        return this.httpClient.delete<Usuario>(`${this.authURL}/delete/${id}`);
    }

    public updateUser(user: string, file: File, id: number): Observable<Usuario> {
        var data = new FormData();
        data.append('imageFile', file);
        data.append('usuario', user);
        return this.httpClient.put<any>(`${this.authURL}/update/${id}`, data);
    }
}
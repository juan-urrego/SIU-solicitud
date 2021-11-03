import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
    providedIn: 'root'
})

export class UserService {

    authURL = 'http://localhost:9191/auth';

    constructor(private httpClient: HttpClient) { }

   
    public getUser(id: number): Observable<User> {
        return this.httpClient.get<User>(`${this.authURL}/user/${id}`);
    }

    public getUsers(): Observable<User[]> {
        return this.httpClient.get<User[]>(`${this.authURL}/users`);
    }

    public getUserFirma(id: number): Observable<Blob> {
        return this.httpClient.get(`${this.authURL}/image/${id}`, {responseType: 'blob'});
    }

    public getDirectorActivo(): Observable<User> {
        return this.httpClient.get<User>(`${this.authURL}/directorActive`);
    }

    public updateByActive(id: number, isActive: any): Observable<User> {
        const data = new FormData();
        data.append('isActive', isActive);
        return this.httpClient.put<User>(`${this.authURL}/isActive/${id}`, data);
    }
    
    public createUser(user: string, file: File): Observable<User> {
        var data = new FormData();
        data.append('imageFile', file);
        data.append('user', user);
        return this.httpClient.post<User>(`${this.authURL}/new`, data);
    }

    public deleteUser(id: number): Observable<{}> {
        return this.httpClient.delete<User>(`${this.authURL}/delete/${id}`);
    }

    public updateUser(user: string, file: File, id: number): Observable<User> {
        var data = new FormData();
        data.append('imageFile', file);
        data.append('user', user);
        return this.httpClient.put<any>(`${this.authURL}/update/${id}`, data);
    }
}
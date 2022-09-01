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

    public getUserByEmail(email: string): Observable<User> {
        return this.httpClient.get<User>(`${this.authURL}/user/email/${email}`);
    }

    public getUsers(): Observable<User[]> {
        return this.httpClient.get<User[]>(`${this.authURL}/users`);
    }

    public deleteUser(id: number): Observable<{}> {
        return this.httpClient.delete<User>(`${this.authURL}/delete/${id}`);
    }

    public createUser(user: User, file: File): Observable<User> {
        var data = new FormData();
        data.append('name', user.name);
        data.append('email', user.email);
        data.append('position', user.position);
        data.append('password', user.password);
        data.append('role', user.roles[0].roleName);
        data.append('imageFile', file);
        return this.httpClient.post<User>(`${this.authURL}/new`, data);
    }

    public updateUser(user: User, file: File, id: number): Observable<User> {
        var data = new FormData();
        data.append('name', user.name);
        data.append('email', user.email);
        data.append('position', user.position);
        data.append('password', user.password);
        data.append('imageFile', file);
        return this.httpClient.put<any>(`${this.authURL}/update/${id}`, data);
    }

    public getDirectorActivo(): Observable<User> {
        return this.httpClient.get<User>(`${this.authURL}/directorActive`);
    }

    public getFirmaById(id: number): Observable<Blob> {
        return this.httpClient.get(`${this.authURL}/image/${id}`, {responseType: 'blob'});
    }

    public activeUser(id: number, isActive: any): Observable<User> {
        const data = new FormData();
        data.append('isActive', isActive);
        return this.httpClient.put<User>(`${this.authURL}/isActive/${id}`, data);
    }
}
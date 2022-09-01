import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Mail, Solicitud } from './solicitud';


@Injectable({providedIn: 'root'})
export class SolicitudService {
    private solicitudUrl = 'http://localhost:9191/solicitud';

    constructor(private http: HttpClient) { }

    getSolicitudes(): Observable<Solicitud[]> {
        return this.http.get<Solicitud[]>(`${this.solicitudUrl}/solicitudes`);
    }

    descargarPdf(id: number) {
        return this.http.get(`${this.solicitudUrl}/solicitudes/${id}`);
    }

    getSolicitud(id: number): Observable<Solicitud> {
        return this.http.get<Solicitud>(`${this.solicitudUrl}/${id}`);
    }

    deleteSolicitud(id: number): Observable<{}> {
        return this.http.delete<Solicitud>(`${this.solicitudUrl}/delete/${id}`)
    }

    createSolicitud(solicitud: Solicitud): Observable<Solicitud> {
        return this.http.post<Solicitud>(`${this.solicitudUrl}/save`, solicitud);
    }

    updateSolicitud(solicitud: Solicitud): Observable<Solicitud> {
        return this.http.put<Solicitud>(`${this.solicitudUrl}/update/${solicitud.id}`, solicitud);
    }

    confirmarSolicitud(id: number): Observable<{}> {
        return this.http.put(`${this.solicitudUrl}/confirmar/${id}`, null);
    }

    crearDocumentos(idSolicitud: number): Observable<{}> {
        return this.http.post(`${this.solicitudUrl}/crear/${idSolicitud}`, null);
    }

    enviarCorreo(id: number, mail: Mail): Observable<{}> {
        return this.http.put(`${this.solicitudUrl}/email/${id}`, mail);
    }
}
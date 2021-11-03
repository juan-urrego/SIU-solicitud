import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

import { Solicitud } from './solicitud';
import { SolicitudService } from './solicitud.service';
import { AuthService } from '../auth/auth.service';
import { ConfirmationService, MenuItem, MessageService } from 'primeng/api';
import { LowerCasePipe } from '@angular/common';

@Component({
    templateUrl: 'solicitud-listar.component.html'
})

export class SolicitudListarComponent implements OnInit {
    titulo: string = 'Lista de solicitudes'
    mensajeError: string;
    update: FormGroup;
    items: MenuItem[];



    solicitudes: Solicitud[];

    constructor(private solicitudService: SolicitudService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

    ngOnInit(): void {
        this.refresh();
    }

    refresh() {
        this.solicitudService.getSolicitudes().subscribe({
            next: solicitudes => {
                this.solicitudes = solicitudes;;
            },
            error: err => this.mensajeError = err
        });
    }

    createDocuments(idSolicitud: number):void {
        this.confirmationService.confirm({
            message: '¿Estás seguro de crear los siguientes documentos?: Estudios previos, Consulta de precios',
            header: 'Confirmacion',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.solicitudService.crearDocumentos(idSolicitud).subscribe({
                    next: () => {
                        this.refresh();
                        this.messageService.add({
                            severity:'success',
                            summary: 'Verificado'
                        });
                    },
                    error: error => {
                        this.mensajeError = error
                        this.messageService.add({
                            severity:'error',
                            summary: 'Error',
                            detail: this.mensajeError
                        });
                    }
                });
            }
        });
        
    }

    verifyDocument(id:number): void {
        this.confirmationService.confirm({
            message: '¿Estás seguro de confirmar esta solicitud?',
            header: 'Confirmacion',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.solicitudService.confirmarSolicitud(id).subscribe({
                    next: () => {
                        this.refresh();
                        this.messageService.add({
                            severity:'success',
                            summary: 'Verificado'
                        });
                    },
                    error: error => {
                        this.mensajeError = error
                        this.messageService.add({
                            severity:'error',
                            summary: 'Error',
                            detail: this.mensajeError
                        });
                    }
                });
            }
        });
    }

}

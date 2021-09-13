import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { AuthService } from '../auth/auth.service';
import { Estudio } from './estudio';
import { EstudioService } from './estudio.service';

@Component({    
    templateUrl: 'estudio-listar.component.html'
})

export class EstudioListarComponent implements OnInit {
    titulo: string = 'Lista de estudios'
    mensajeError: string;
    estudios: Estudio[]


    constructor(private estudioService: EstudioService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

    ngOnInit(): void {
        this.refresh();
    }

    refresh() {
        this.estudioService.getEstudios().subscribe({
            next: estudios => {
                this.estudios = estudios;;
            },
            error: err => this.mensajeError = err
        });
    }

    verifyDocument(id:number): void {
        this.confirmationService.confirm({
            message: '¿Estás seguro de confirmar esta solicitud?',
            header: 'Confirmacion',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.estudioService.confirmarEstudio(id).subscribe({
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
import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { AuthService } from '../auth/auth.service';
import { Consulta } from './consulta';
import { ConsultaService } from './consulta.service';

@Component({
    
    templateUrl: 'consulta-listar.component.html'
})

export class ConsultaListarComponent implements OnInit {
    titulo: string = 'Lista de consultas'
    mensajeError: string;


    consultas: Consulta[]
    constructor(private consultaService: ConsultaService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

    ngOnInit(): void {
        this.refresh();
    }

    refresh() {
        this.consultaService.getConsultas().subscribe({
            next: consultas => {
                this.consultas = consultas;;
            },
            error: err => this.mensajeError = err
        });
    }

    verifyDocument(id:number): void {
        this.confirmationService.confirm({
            message: '¿Estás seguro de verificar esta consulta?',
            header: 'Confirmacion',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.consultaService.confirmarConsulta(id).subscribe({
                    next: () => {
                        this.refresh();
                        this.messageService.add({
                            severity:'success',
                            summary: 'Verificado'
                        });
                    },
                    error: error => {
                        this.mensajeError = error.error.message
                        console.log(error.message);
                        
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
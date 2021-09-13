import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Parametro } from '../parametro';
import { ParametroConsultaService } from './parametroConsulta.service';

@Component({
    selector: 'pm-consulta',
    templateUrl: 'parametro-consulta.component.html'
})

export class ParametroConsultaComponent implements OnInit {
    
    consultas: Parametro[];
    consultaSelected: any = {};
    consultaDialog: boolean;
    consultaForm: FormGroup;
    submitted: boolean;
    mensajerError: string;
    delay = 3000;

    constructor(
        private parametroConsultaService: ParametroConsultaService,
        private fb: FormBuilder,
        private confirmationService: ConfirmationService,
        private messageService: MessageService) { }

    ngOnInit(): void { 
        this.refresh();
    }

    updateSelected(consultaSelected: Parametro) {
        this.parametroConsultaService.updateParametroConsultaActivo(consultaSelected.id)
        .subscribe({
            next: () => {
                this.refresh();
                this.messageService.add({
                    severity:'success',
                    summary: 'Exito',
                    detail: 'Parametro Activado',
                    life: this.delay
                });
            },
            error: error => {
                this.mensajerError = error;
                this.messageService.add({
                    severity:'error',
                    summary: 'Error',
                    detail: this.mensajerError,
                    life: this.delay
                });
            }
        });
    }

    deleteSelected() {
        this.parametroConsultaService.deleteParametroConsultaActivo().subscribe({
            next: () => {
                this.refresh();
                this.messageService.add({
                    severity:'success',
                    summary: 'Exito',
                    detail: 'Parametro Activado',
                    life: this.delay
                });
            },
            error: error => {
                this.mensajerError = error.message;
                this.messageService.add({
                    severity:'error',
                    summary: 'Error',
                    detail: this.mensajerError,
                    life: this.delay
                });
            }
        })
    }

    refresh() {
        this.parametroConsultaService.getParametroConsultas().subscribe({
            next: consultas => {
                this.consultas = consultas;
                if (this.consultas){
                    this.consultaSelected = this.consultas.find(t => t.parametro == 1);
                }
            },
            error: error => this.mensajerError = error
        });
    }

    openNew(parametroSelected ?: Parametro){
        this.consultaForm = this.fb.group({
            id: 0,
            descripcion: ['', Validators.required]
        });
        if(parametroSelected){
            this.consultaForm.patchValue({
                id: parametroSelected.id,
                descripcion: parametroSelected.descripcion
            });
        }
        this.submitted = false;
        this.consultaDialog = true;
    }

    hideDialog(){
        this.consultaForm.reset();
        this.consultaDialog = false;
        this.submitted = false;
    }

    saveConsulta(){
        if(this.consultaForm.valid){
            if(this.consultaForm.dirty){
                const p = { ...this.consultaSelected, ...this.consultaForm.value}

                if(this.consultaForm.get('id').value === 0){
                    this.parametroConsultaService.createParametroConsulta(p)
                        .subscribe({
                            next: () => {
                                this.refresh();
                                this.hideDialog();
                                this.messageService.add({
                                    severity:'success',
                                    summary: 'Exito',
                                    detail: 'Parametro Creado',
                                    life: this.delay
                                });
                            },
                            error: error => {
                                this.mensajerError = error;
                                this.messageService.add({
                                    severity:'error',
                                    summary: 'Error',
                                    detail: this.mensajerError,
                                    life: this.delay
                                });
                            }
                        });
                }else {
                    this.parametroConsultaService.updateParametroConsulta(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Parametro Actualizado',
                                life: this.delay
                            });
                        },
                        error: error => {
                            this.mensajerError = error;
                            this.messageService.add({
                                severity:'error',
                                summary: 'Error',
                                detail: this.mensajerError,
                                life: this.delay
                            });
                        }
                    });

                }
            }
        }
    }

    deleteConsulta(){
        if(this.consultaForm.get('id').value != 0){
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar este parametro?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.parametroConsultaService.deleteParametroConsulta(this.consultaForm.get('id').value).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Parametro Eliminado',
                                life: this.delay
                            });
                        },
                        error: error => {
                            this.mensajerError = error
                            this.messageService.add({
                                severity:'error',
                                summary: 'Error',
                                detail: this.mensajerError,
                                life: this.delay
                            });
                        }
                    });
                }
            });
        }
    }
}
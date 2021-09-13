import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Parametro } from '../parametro';
import { ParametroAcuerdoService } from './parametroAcuerdo.service';

@Component({
    selector: 'pm-acuerdo',
    templateUrl: 'parametro-acuerdo.component.html'
})

export class ParametroAcuerdoComponent implements OnInit {
    
    acuerdos: Parametro[];
    mensajerError: string;
    acuerdoDialog: boolean;
    acuerdoForm: FormGroup;    
    submitted: boolean
    acuerdoSelected: any= {};
    delay = 3000;
    

    constructor(
        private parametroAcuerdoService: ParametroAcuerdoService,
        private fb: FormBuilder,
        private confirmationService: ConfirmationService,
        private messageService: MessageService) { }

    ngOnInit(): void { 
        this.refresh();
    }

    updateSelected(acuerdoSelected: Parametro) {
        this.parametroAcuerdoService.updateParametroAcuerdoActivo(acuerdoSelected.id)
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
                this.mensajerError = error.message;
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
        this.parametroAcuerdoService.deleteParametroAcuerdoActivo().subscribe({
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
                console.log(this.mensajerError);
                
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
        this.parametroAcuerdoService.getParametroAcuerdos().subscribe({
            next: acuerdos => {
                this.acuerdos = acuerdos;
                if (this.acuerdos) {
                    this.acuerdoSelected = this.acuerdos.find(t => t.parametro == 1);
                }
            },
            error: error => this.mensajerError = error
        });
    }

    openNew(parametroSelected ?: Parametro){
        this.acuerdoForm = this.fb.group({
            id: 0,
            descripcion: ['', Validators.required]
        });
        if(parametroSelected){
            this.acuerdoForm.patchValue({
                id: parametroSelected.id,
                descripcion : parametroSelected.descripcion
            });      
        }
        this.submitted = false;              
        this.acuerdoDialog = true;
    }

    hideDialog(){            
        this.acuerdoForm.reset();
        this.acuerdoDialog = false;
        this.submitted = false;        
    }

    saveAcuerdo(){
        if(this.acuerdoForm.valid){
            if(this.acuerdoForm.dirty){
                const p = { ...this.acuerdoSelected, ...this.acuerdoForm.value};

                if(this.acuerdoForm.get('id').value === 0) {
                    this.parametroAcuerdoService.createParametroAcuerdo(p).subscribe({
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
                }else{
                    this.parametroAcuerdoService.updateParametroAcuerdo(p).subscribe({
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

    deleteAcuerdo(){
        if(this.acuerdoForm.get('id').value != 0){
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar este parametro?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.parametroAcuerdoService.deleteParametroAcuerdo(this.acuerdoForm.get('id').value).subscribe({
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
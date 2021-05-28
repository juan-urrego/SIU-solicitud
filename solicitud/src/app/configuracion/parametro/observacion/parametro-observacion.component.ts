import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Parametro } from '../parametro';
import { ParametroObservacionService } from './parametroObservacion.service';

@Component({
    selector: 'pm-observacion',
    templateUrl: 'parametro-observacion.component.html'
})

export class ParametroObservacionComponent implements OnInit {

    observaciones: Parametro[];
    mensajerError: string;
    observacionDialog: boolean;
    observacionForm: FormGroup;    
    submitted: boolean
    observacionSelected: any= {};
    delay = 3000;
    

    constructor(
        private parametroObservacionService: ParametroObservacionService,
        private fb: FormBuilder,
        private confirmationService: ConfirmationService,
        private messageService: MessageService) { }

    ngOnInit(): void { 
        this.refresh();
    }

    updateSelected(observacionSelected: Parametro) {
        this.parametroObservacionService.updateParametroObservacionActivo(observacionSelected.id)
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

    refresh() {
        this.parametroObservacionService.getParametroObservaciones().subscribe({
            next: observaciones => {
                this.observaciones = observaciones;
                this.observacionSelected = this.observaciones.find(t => t.parametro == 1);
            },
            error: error => this.mensajerError = error
        });
    }

    openNew(parametroSelected ?: Parametro){
        this.observacionForm = this.fb.group({
            id: 0,
            descripcion: ['', Validators.required]
        });
        if(parametroSelected){
            this.observacionForm.patchValue({
                id: parametroSelected.id,
                descripcion : parametroSelected.descripcion
            });      
        }
        this.submitted = false;              
        this.observacionDialog = true;
    }

    hideDialog(){            
        this.observacionForm.reset();
        this.observacionDialog = false;
        this.submitted = false;        
    }

    saveObservacion(){
        if(this.observacionForm.valid){
            if(this.observacionForm.dirty){
                const p = { ...this.observacionSelected, ...this.observacionForm.value};

                if(this.observacionForm.get('id').value === 0) {
                    this.parametroObservacionService.createParametroObservacion(p).subscribe({
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
                    this.parametroObservacionService.updateParametroObservacion(p).subscribe({
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

    deleteObservacion(){
        if(this.observacionForm.get('id').value != 0){
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar este parametro?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.parametroObservacionService.deleteParametroObservacion(this.observacionForm.get('id').value).subscribe({
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
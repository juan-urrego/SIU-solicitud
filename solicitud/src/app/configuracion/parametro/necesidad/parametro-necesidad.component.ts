import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Parametro } from '../parametro';
import { ParametroNecesidadService } from './parametroNecesidad.service';

@Component({
    selector: 'pm-necesidad',
    templateUrl: 'parametro-necesidad.component.html'
})

export class ParametroNecesidadComponent implements OnInit {

    necesidades: Parametro[];
    mensajerError: string;
    necesidadDialog: boolean;
    necesidadForm: FormGroup;    
    submitted: boolean
    necesidadSelected: any= {};
    delay = 3000;
    

    constructor(
        private parametroNecesidadService: ParametroNecesidadService,
        private fb: FormBuilder,
        private confirmationService: ConfirmationService,
        private messageService: MessageService) { }

    ngOnInit(): void { 
        this.refresh();
    }

    updateSelected(necesidadSelected: Parametro) {
        this.parametroNecesidadService.updateParametroNecesidadActivo(necesidadSelected.id)
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
        this.parametroNecesidadService.deleteParametroNecesidadActivo().subscribe({
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
        this.parametroNecesidadService.getParametroNecesidades().subscribe({
            next: necesidades => {
                this.necesidades = necesidades;
                if (this.necesidades) {
                    this.necesidadSelected = this.necesidades.find(t => t.parametro == 1);
                }
            },
            error: error => this.mensajerError = error
        });
    }

    openNew(parametroSelected ?: Parametro){
        this.necesidadForm = this.fb.group({
            id: 0,
            descripcion: ['', Validators.required]
        });
        if(parametroSelected){
            this.necesidadForm.patchValue({
                id: parametroSelected.id,
                descripcion : parametroSelected.descripcion
            });      
        }
        this.submitted = false;              
        this.necesidadDialog = true;
    }

    hideDialog(){            
        this.necesidadForm.reset();
        this.necesidadDialog = false;
        this.submitted = false;        
    }

    saveNecesidad(){
        if(this.necesidadForm.valid){
            if(this.necesidadForm.dirty){
                const p = { ...this.necesidadSelected, ...this.necesidadForm.value};

                if(this.necesidadForm.get('id').value === 0) {
                    this.parametroNecesidadService.createParametroNecesidad(p).subscribe({
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
                    this.parametroNecesidadService.updateParametroNecesidad(p).subscribe({
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

    deleteNecesidad(){
        if(this.necesidadForm.get('id').value != 0){
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar este parametro?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.parametroNecesidadService.deleteParametroNecesidad(this.necesidadForm.get('id').value).subscribe({
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
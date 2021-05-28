import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Parametro } from '../parametro';
import { ParametroArgumentoService } from './parametroArgumento.service';

@Component({
    selector: 'pm-argumento',
    templateUrl: 'parametro-argumento.component.html'
})

export class ParametroArgumentoComponent implements OnInit {
    
    argumentos: Parametro[];
    argumentoSelected: Parametro;
    argumentoDialog: boolean;
    argumentoForm: FormGroup;
    mensajeError: string;
    submitted: boolean;
    delay = 3000;

    constructor(
        private argumentoService: ParametroArgumentoService,
        private fb: FormBuilder,
        private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

    ngOnInit(): void { 
        this.refresh();
    }

    refresh() {
        this.argumentoService.getParametroArgumentos().subscribe({
            next: argumentos => {
                this.argumentos = argumentos;                
            },
            error: error => this.mensajeError = error
        });
    }

    openNew(parametro ?: Parametro){
        this.argumentoForm = this.fb.group({
            id: 0,
            descripcion: ['', Validators.required]
        });
        if(parametro){
            this.argumentoForm.patchValue({
                id: parametro.id,
                descripcion : parametro.descripcion
            });      
        }
        this.submitted = false;              
        this.argumentoDialog = true;
    }

    hideDialog(){            
        this.argumentoForm.reset();
        this.argumentoDialog = false;
        this.submitted = false;        
    }

    saveArgumento(){
        if(this.argumentoForm.valid){
            if(this.argumentoForm.dirty){
                const p = { ...this.argumentoSelected, ...this.argumentoForm.value};

                if(this.argumentoForm.get('id').value === 0) {
                    this.argumentoService.createParametroArgumento(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Argumento Creado',
                                life: this.delay
                            });
                        },
                        error: error => {
                            this.mensajeError= error;
                            this.messageService.add({
                                severity:'error',
                                summary: 'Error',
                                detail: this.mensajeError,
                                life: this.delay
                            });
                        }
                    });
                }else{
                    this.argumentoService.updateParametroArgumento(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Argumento Actualizado',
                                life: this.delay
                            });
                        },
                        error: error => {
                            this.mensajeError = error;
                            this.messageService.add({
                                severity:'error',
                                summary: 'Error',
                                detail: this.mensajeError,
                                life: this.delay
                            });
                        }
                    });
                }
            }
        }
    }

    //analizar cuando se elimina, afecta a los estudios ya creados
    deleteArgumento(){
        if(this.argumentoForm.get('id').value != 0){
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar esta argumento?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.argumentoService.deleteParametroArgumento(this.argumentoForm.get('id').value).subscribe({
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
                            this.mensajeError = error
                            this.messageService.add({
                                severity:'error',
                                summary: 'Error',
                                detail: this.mensajeError,
                                life: this.delay
                            });
                        }
                    });
                }
            });
        }
    }
}
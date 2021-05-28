import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Parametro } from '../parametro';
import { UnidadAcademicaService } from './unidadAcademica.service';

@Component({
    selector: 'pm-academica',
    templateUrl: 'parametro-academica.component.html'
})

export class ParametroAcademicaComponent implements OnInit {

    unidadesAcademicas: Parametro[];
    unidadSelected: Parametro;
    unidadDialog: boolean;
    unidadForm: FormGroup;
    mensajeError: string;
    submitted: boolean;
    delay = 3000;

    constructor(
        private unidadAcademicaService: UnidadAcademicaService,
        private fb: FormBuilder,
        private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

    ngOnInit(): void { 
        this.refresh();
    }

    refresh() {
        this.unidadAcademicaService.getUnidadAcademicas().subscribe({
            next: unidades => {
                this.unidadesAcademicas = unidades;                
            },
            error: error => this.mensajeError = error
        });
    }

    openNew(parametro ?: Parametro){
        this.unidadForm = this.fb.group({
            id: 0,
            descripcion: ['', Validators.required]
        });
        if(parametro){
            this.unidadForm.patchValue({
                id: parametro.id,
                descripcion : parametro.descripcion
            });      
        }
        this.submitted = false;              
        this.unidadDialog = true;
    }

    hideDialog(){            
        this.unidadForm.reset();
        this.unidadDialog = false;
        this.submitted = false;        
    }

    saveUnidad(){
        if(this.unidadForm.valid){
            if(this.unidadForm.dirty){
                const p = { ...this.unidadSelected, ...this.unidadForm.value};

                if(this.unidadForm.get('id').value === 0) {
                    this.unidadAcademicaService.createUnidadAcademica(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Unidad Academica Creada',
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
                    this.unidadAcademicaService.updateUnidadAcademica(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Unidad Academica Actualizada',
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
    deleteUnidad(){
        if(this.unidadForm.get('id').value != 0){
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar esta unidad Academica?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.unidadAcademicaService.deleteUnidadAcademica(this.unidadForm.get('id').value).subscribe({
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
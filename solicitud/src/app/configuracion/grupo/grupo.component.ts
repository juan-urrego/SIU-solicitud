import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Grupo } from './grupo';
import { GrupoService } from './grupo.service';

@Component({
    templateUrl: 'grupo.component.html'
})

export class GrupoComponent implements OnInit {
    titulo: string = 'Lista de grupos'
    mensajeError: string;    
    grupos: Grupo[];
    grupoSelected: Grupo;
    grupoDialog: boolean;
    grupoForm: FormGroup;
    submitted: boolean;
    delayToast = 3000;

    constructor(
        private grupoService: GrupoService,
        private fb: FormBuilder,
        private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

    ngOnInit(): void {
        this.refresh();
    }
    
    refresh(): void {
        this.grupoService.getGrupos().subscribe({
            next: grupos => this.grupos = grupos,
            error: error => this.mensajeError = error
        });
    }

    openNew(grupo ?: Grupo) {
        this.grupoForm = this.fb.group({
            id: 0,
            nombre: ['', Validators.required],
            codColciencia: ['', Validators.required]
        });
        if(grupo) {
            this.grupoForm.patchValue({
                id: grupo.id,
                nombre: grupo.nombre,
                codColciencia: grupo.codColciencia
            });
            this.grupoSelected = grupo;
        }
        this.submitted = false;
        this.grupoDialog = true;
    }

    hideDialog() {
        this.grupoForm.reset();
        this.grupoDialog = false;
        this.submitted = false;
    }

    saveGrupo(){
        if(this.grupoForm.valid){
            if(this.grupoForm.dirty){
                const p = { ...this.grupoSelected, ...this.grupoForm.value};

                if(this.grupoForm.get('id').value === 0) {
                    this.grupoService.createGrupo(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Grupo Creado',
                                life: this.delayToast
                            });
                        },
                        error: error => {
                            this.mensajeError= error;
                            this.messageService.add({
                                severity:'error',
                                summary: 'Error',
                                detail: this.mensajeError,
                                life: this.delayToast
                            });
                        }
                    });
                }else{
                    this.grupoService.updateGrupo(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Grupo Actualizado',
                                life: this.delayToast
                            });
                        },
                        error: error => {
                            this.mensajeError = error;
                            this.messageService.add({
                                severity:'error',
                                summary: 'Error',
                                detail: this.mensajeError,
                                life: this.delayToast
                            });
                        }
                    });
                }
            }
        }
    }

    deleteGrupo(grupo : Grupo){
        if(grupo.id != 0){
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar este grupo?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.grupoService.deleteGrupo(grupo.id).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Grupo Eliminado',
                                life: this.delayToast
                            });
                        },
                        error: error => {
                            this.mensajeError = error
                            this.messageService.add({
                                severity:'error',
                                summary: 'Error',
                                detail: this.mensajeError,
                                life: this.delayToast
                            });
                        }
                    });
                }
            });
        }
    }
}
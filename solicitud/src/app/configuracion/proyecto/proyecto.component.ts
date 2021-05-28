import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Grupo } from '../grupo/grupo';
import { GrupoService } from '../grupo/grupo.service';
import { Proyecto } from './proyecto';
import { ProyectoService } from './proyecto.service';

@Component({
    templateUrl: 'proyecto.component.html'
})

export class ProyectoComponent implements OnInit {
    titulo: string = 'Lista de grupos'
    mensajeError: string;    
    proyectos: Proyecto[];
    proyectoSelected: Proyecto;
    proyectoDialog: boolean;
    proyectoForm: FormGroup;
    submitted: boolean;
    grupos: Grupo[];
    delayToast = 3000;

    constructor(
        private proyectoService: ProyectoService,
        private grupoService: GrupoService,
        private fb: FormBuilder,
        private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

    ngOnInit(): void {
        this.refresh();
    }
    
    refresh(): void {
        this.proyectoService.getProyectos().subscribe({
            next: proyectos => this.proyectos = proyectos,
            error: error => this.mensajeError = error
        });
    }

    loadGrupos(): void {
        this.grupoService.getGrupos().subscribe({
            next: grupos => this.grupos = grupos,
            error: error => this.mensajeError = error
        });
    }

    openNew(proyecto ?: Proyecto) {
        this.loadGrupos();
        this.proyectoForm = this.fb.group({
            id: 0,
            nombre: ['', Validators.required],
            codigoProyecto: ['', Validators.required],
            centroCostos : ['', Validators.required],
            grupo: ['', Validators.required]
        });
        if(proyecto) {
            this.proyectoForm.patchValue({
                id: proyecto.id,
                nombre: proyecto.nombre,
                codigoProyecto: proyecto.codigoProyecto,
                centroCostos: proyecto.centroCostos,
                grupo: proyecto.grupo.id                
            });
            this.proyectoSelected = proyecto;
        }
        this.submitted = false;        
        this.proyectoDialog = true;
    }

    hideDialog() {
        this.proyectoForm.reset();
        this.proyectoDialog = false;
        this.submitted = false;
    }

    saveProyecto(){
        if(this.proyectoForm.valid){
            if(this.proyectoForm.dirty){
                const p = { ...this.proyectoSelected, ...this.proyectoForm.value};

                if(this.proyectoForm.get('id').value === 0) {
                    this.proyectoService.createProyecto(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Proyecto Creado',
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
                    this.proyectoService.updateProyecto(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Proyecto Actualizado',
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

    deleteProyecto(proyecto : Proyecto){
        if(proyecto.id != 0){
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar este proyecto?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.proyectoService.deleteProyecto(proyecto.id).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Proyecto Eliminado',
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
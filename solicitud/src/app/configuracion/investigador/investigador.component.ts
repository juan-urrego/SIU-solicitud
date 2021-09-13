import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Investigador } from './investigador';
import { InvestigadorService } from './investigador.service';

@Component({
    templateUrl: 'investigador.component.html'
})

export class InvestigadorComponent implements OnInit {
    titulo: string = 'Lista de Investigadores'
    mensajeError: string;    
    investigadores: Investigador[];
    investigadorSelected: Investigador;
    investigadorDialog: boolean;
    investigadorForm: FormGroup;
    submitted: boolean;
    delayToast = 3000;

    constructor(
        private investigadorService: InvestigadorService,
        private fb: FormBuilder,
        private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

    ngOnInit(): void {
        this.refresh();
    }
    
    refresh(): void {
        this.investigadorService.getInvestigadores().subscribe({
            next: investigadores => this.investigadores = investigadores,
            error: error => this.mensajeError = error
        });
    }

    openNew(investigador ?: Investigador) {
        this.investigadorForm = this.fb.group({            
            identificacion: ['', Validators.required],
            nombre: ['', Validators.required],
            telefono: ['', Validators.required],
            email: ['', Validators.required]
        });
        if(investigador) {
            this.investigadorForm.patchValue({                
                identificacion: investigador.identificacion,
                nombre: investigador.nombre,
                telefono: investigador.telefono,
                email: investigador.email
            });         
            this.investigadorSelected = investigador;
        }
        this.submitted = false;
        this.investigadorDialog = true;
    }

    
    hideDialog() {
        this.investigadorForm.reset();
        this.investigadorDialog = false;
        this.submitted = false;                
    }

    

    saveInvestigador(){
        if(this.investigadorForm.valid){
            if(this.investigadorForm.dirty){                
                
                if(this.investigadorSelected) {
                    this.investigadorService.createInvestigador(JSON.stringify(this.investigadorForm.value),null).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Investigador Creado',
                                life: this.delayToast
                            });
                        },
                        error: error => {
                            this.mensajeError= error.message;
                            console.log(typeof(JSON.stringify(this.investigadorForm.value)));
                            
                            this.messageService.add({
                                severity:'error',
                                summary: 'Error',
                                detail: this.mensajeError,
                                life: this.delayToast
                            });
                        }
                    });
                }else{
                    this.investigadorService.updateInvestigador(this.investigadorForm.value,null, this.investigadorForm.get('id').value).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Investigador Actualizado',
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

    deleteInvestigador(investigador : Investigador){
        if(investigador.id != 0){
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar este investigador?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.investigadorService.deleteInvestigador(investigador.id).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Investigador Eliminado',
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
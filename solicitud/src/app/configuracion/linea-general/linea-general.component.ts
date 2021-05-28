import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { LineaGeneral } from './lineaGeneral';
import { LineaGeneralService } from './lineaGeneral.service';

@Component({
    templateUrl: 'linea-general.component.html'
})

export class LineaGeneralComponent implements OnInit {
    titulo: string = 'Lista de grupos'
    mensajeError: string;    
    lineas: LineaGeneral[];
    lineaSelected: LineaGeneral;
    lineaDialog: boolean;
    lineaForm: FormGroup;
    submitted: boolean;    
    delayToast = 3000;

    get lineasEspecificas(): FormArray{
        return <FormArray>this.lineaForm.get('lineaEspecificas')
    }

    constructor(
        private lineaGeneralService: LineaGeneralService,    
        private fb: FormBuilder,
        private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

    ngOnInit(): void {
        this.refresh();
    }
    
    refresh(): void {
        this.lineaGeneralService.getLineaGenerales().subscribe({
            next: lineas => this.lineas = lineas,
            error: error => this.mensajeError = error
        });
    }


    openNew(linea ?: LineaGeneral) {                
        this.lineaForm = this.fb.group({
            id: 0,
            nombre: ['', Validators.required],
            lineaEspecificas: this.fb.array([])        
        });
        if(linea) {
            this.lineaForm.patchValue({
                id: linea.id,
                nombre: linea.nombre                
            });
            linea.lineaEspecificas.forEach((x) => {
                this.lineasEspecificas.push(this.fb.group(x))
            });                            
            this.lineaSelected = linea;
        }else{
            this.addLineasEspecificas();
        }
        this.submitted = false;        
        this.lineaDialog = true;
    }

    hideDialog() {
        this.lineaForm.reset();
        this.lineaDialog = false;
        this.submitted = false;
    }


    addLineasEspecificas(): void {        
        this.lineasEspecificas.push(
            this.fb.group({
            nombre: ''
            })
        );
    }

    deleteLineasEspecificas(index: number): void {
        this.lineasEspecificas.removeAt(index);
        this.lineasEspecificas.markAsDirty();
    }


    saveLinea(){
        if(this.lineaForm.valid){
            if(this.lineaForm.dirty){
                const p = { ...this.lineaSelected, ...this.lineaForm.value};

                if(this.lineaForm.get('id').value === 0) {
                    this.lineaGeneralService.createLineaGeneral(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'General Creada',
                                life: this.delayToast
                            });
                        },
                        error: error => {
                            this.mensajeError= error.message;
                            this.messageService.add({
                                severity:'error',
                                summary: 'Error',
                                detail: this.mensajeError,
                                life: this.delayToast
                            });
                        }
                    });
                }else{
                    this.lineaGeneralService.updateLineaGeneral(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Linea General Actualizada',
                                life: this.delayToast
                            });
                        },
                        error: error => {
                            this.mensajeError = error.message;
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

    deleteLinea(linea : LineaGeneral){
        if(linea.id != 0){
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar esta linea general?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.lineaGeneralService.deleteLineaGeneral(linea.id).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Linea general Eliminada',
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
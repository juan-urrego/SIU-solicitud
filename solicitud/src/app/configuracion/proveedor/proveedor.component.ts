import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Proveedor } from './proveedor';
import { ProveedorService } from './proveedor.service';

@Component({
    templateUrl: 'proveedor.component.html'
})

export class ProveedorComponent implements OnInit {
    titulo: string = 'Lista de Proveedores'
    mensajeError: string;
    proveedores: Proveedor[];
    proveedorSelected: Proveedor;
    proveedorDialog: boolean;
    submitted: boolean;
    proveedorForm: FormGroup;
    delayToast = 3000;

    
    constructor(private proveedorService: ProveedorService,
        private fb: FormBuilder,
        private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

    ngOnInit(): void {
        this.refresh();
    }

    refresh(): void {
        this.proveedorService.getProveedores().subscribe({
            next: proveedores => this.proveedores = proveedores,
            error: error => this.mensajeError = error 
        });
    }

    openNew(proveedor ?: Proveedor): void {
        this.proveedorForm = this.fb.group({
            id: 0,
            nombre: ['', Validators.required],
            ciudad: ['', Validators.required],
            identificacion: ['', Validators.required],
            telefono: ['', Validators.required],
            tipo: ['', Validators.required]
        });
        if(proveedor){
            this.proveedorForm.patchValue({
                id: proveedor.id,
                nombre: proveedor.nombre,
                ciudad: proveedor.ciudad,
                identificacion: proveedor.identificacion,
                telefono: proveedor.telefono,
                tipo: proveedor.tipo
            });
            this.proveedorSelected = proveedor;
        }
        this.submitted= false;
        this.proveedorDialog = true;
    }

    hideDialog() {
        this.proveedorForm.reset();
        this.proveedorDialog = false;
        this.submitted = false;
    }

    saveProveedor() {
        if(this.proveedorForm.valid){
            console.log("esto es uns save");
            if(this.proveedorForm.dirty){
                const p = {...this.proveedorSelected, ...this.proveedorForm.value}

                if(!this.proveedorSelected){
                    
                    this.proveedorService.createProveedor(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity: 'success',
                                summary: 'Exito',
                                detail: 'Proveedor Creado',
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
                else{
                    this.proveedorService.updateProveedor(p).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Proveedor Actualizado',
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
                    })
                }
            }
        }
    }

    deleteProveedor(proveedor: Proveedor) {
        if(proveedor.id != 0) {
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar este proveedor?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.proveedorService.deleteProveedor(proveedor.id).subscribe({
                        next: () => {
                            this.refresh();
                            this.hideDialog();
                            this.messageService.add({
                                severity:'success',
                                summary: 'Exito',
                                detail: 'Proveedor Eliminado',
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
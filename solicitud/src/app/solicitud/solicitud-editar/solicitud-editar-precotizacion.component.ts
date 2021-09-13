import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ParametroArgumentoService } from 'src/app/configuracion/parametro/argumento/parametroArgumento.service';
import { ParametroObservacionService } from 'src/app/configuracion/parametro/observacion/parametroObservacion.service';
import { Parametro } from 'src/app/configuracion/parametro/parametro';
import { Proveedor } from 'src/app/configuracion/proveedor/proveedor';
import { ProveedorService } from 'src/app/configuracion/proveedor/proveedor.service';
import { Solicitud } from '../solicitud';

@Component({
    templateUrl: 'solicitud-editar-precotizacion.component.html'
})

export class SolicitudEditarPrecotizacionComponent implements OnInit {
    mensajeError: string;
    solicitudForm: FormGroup;
    solicitud: Solicitud;
    proveedores: Proveedor[];
    argumentos: Parametro[];
    tipoProveedores = ['Persona jurídica', 'Persona natural'];
    proveedorDialog: boolean;
    newProveedor: Proveedor;
    proveedorForm: FormGroup;
    delayToast = 3000;

    get precotizacionDtos(): FormArray {
        return this.solicitudForm.get('precotizacionDtos') as FormArray;
    }
    get argumentoDtos(): FormArray {
        return this.solicitudForm.get('argumentoDtos') as FormArray;
    }

    constructor(
        private fb: FormBuilder,
        private route: ActivatedRoute,
        private proveedorService: ProveedorService,
        private parametroArgumentoService: ParametroArgumentoService,
        private parametroObservacionService: ParametroObservacionService,
        private messageService: MessageService) { }

    ngOnInit() {
        this.route.parent.data.subscribe(data => {
            this.solicitud = data['resolvedData'].solicitud;
            this.solicitudForm = data['resolvedData'].form;
          });
          this.getParametrosArgumentos();
          this.getProveedores();
          if(!this.solicitud){
              this.getParametrosObservacions();
          }
    }

    openNew(): void {
        this.proveedorForm = this.fb.group({
            id: 0,
            nombre: ['', Validators.required],
            ciudad: ['', Validators.required],
            identificacion: ['', Validators.required],
            telefono: ['', Validators.required],
            tipo: ['', Validators.required]
        });        
        this.proveedorDialog = true;
    }

    buildPrecotizacion(): FormGroup {
        return this.fb.group({            
            proveedor: ['', [Validators.required]],
            _identificacion_proveedor: {value: '', disabled: true},
            _telefono_proveedor: {value: '', disabled: true},
            _ciudad_proveedor: {value: '', disabled: true},
            valorTotal: [null, [Validators.required]],
            valorIva: [null, [Validators.required]]            
        });
    }

    addPrecotizacion(): void {        
        this.precotizacionDtos.push(this.buildPrecotizacion());
    }

    deletePrecotizacion(index: number) {
        this.precotizacionDtos.removeAt(index);
        this.precotizacionDtos.markAsDirty();
    }

    buildArgumentos() {
        return this.fb.group({
            descripcion: ['', [Validators.required]]
        });
    }

    addArgumento() {
        this.argumentoDtos.push(this.buildArgumentos());
    }

    deleteArgumento(index: number) {
        this.argumentoDtos.removeAt(index);
        this.solicitudForm.markAsDirty();
    }

    displayProveedor(precotizacionSelected: FormGroup){
        if(precotizacionSelected.controls['proveedor'].value){
            precotizacionSelected.controls['_identificacion_proveedor'].setValue(precotizacionSelected.controls['proveedor'].value.identificacion);
            precotizacionSelected.controls['_telefono_proveedor'].setValue(precotizacionSelected.controls['proveedor'].value.telefono);
            precotizacionSelected.controls['_ciudad_proveedor'].setValue(precotizacionSelected.controls['proveedor'].value.ciudad);
        }
    }

    displayValorSelected() {
        this.solicitudForm.get('_valor_selected').setValue(this.solicitudForm.get('precotizacionDto').value.valorTotal)
    }

    getParametrosObservacions(){
        return this.parametroObservacionService.getParametroObservacionActivo().subscribe({
            next: parametro => {                
                this.solicitudForm.get('observacion').setValue(parametro.descripcion);
            },
            error: error => this.mensajeError = error
        });
    }

    getParametrosArgumentos(){
        return this.parametroArgumentoService.getParametroArgumentos().subscribe({
            next: parametros => this.argumentos = parametros,
            error: error => this.mensajeError = error
        });
    }

    getProveedores() {
        return this.proveedorService.getProveedores().subscribe({
            next: proveedores => {
                this.proveedores = proveedores;                
            },
            error: error => this.mensajeError = error
        });
    }

    hideDialog() {
        this.proveedorForm.reset();
        this.proveedorDialog = false;        
    }

    saveProveedor() {
        if(this.proveedorForm.valid){
            console.log("esto es uns save");
            if(this.proveedorForm.dirty){
                const p = {...this.newProveedor, ...this.proveedorForm.value}
                this.proveedorService.createProveedor(p).subscribe({
                    next: () => {
                        this.getProveedores();
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
        }
    }
}
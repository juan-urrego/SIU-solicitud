import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Proveedor } from '../../../models/settings/proveedor';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { ProveedorService } from '../../../services/settings/proveedor.service';


@Component({
    templateUrl: 'proveedor-editar.component.html'
})

export class ProveedorEditarComponent implements OnInit, OnDestroy {

    title: string;
    mensajeError: string;
    proveedorForm: FormGroup;
    proveedor: Proveedor;
    private sub: Subscription;


    constructor(private fb: FormBuilder,
        private proveedoService: ProveedorService,
        private router: Router,
        private route: ActivatedRoute) { }

    ngOnInit() {
        this.proveedorForm = this.fb.group({
            nombre: ['', [Validators.required, Validators.minLength(3)]],
            nit: ['', [Validators.required]],
            telefono: ['', [Validators.required, Validators.pattern('^[0-9]*$')]]
        });

        this.sub = this.route.paramMap.subscribe(
            params => {
                const id = +params.get('id');
                this.getProveedor(id);
            }
        );
    }

    ngOnDestroy(): void {
        this.sub.unsubscribe();
    }


    getProveedor(id: number) {
        this.proveedoService.getProveedor(id)
            .subscribe({
                next: (proveedor: Proveedor) => this.displayProveedor(proveedor),
                error: err => this.mensajeError = err
            });
    }
    
    displayProveedor(proveedor: Proveedor): void {
        if (this.proveedorForm) {
            this.proveedorForm.reset();
        }
        this.proveedor = proveedor;

        if (this.proveedor.id === 0) {
            this.title = "Agregar Proveedor";
        } else {
            this.title = `Editar Proveedor: ${this.proveedor.nombre}`;
        }

        this.proveedorForm.patchValue({
            nombre: this.proveedor.nombre,
            nit: this.proveedor.nit,
            telefono: this.proveedor.telefono
        });
        
    }


    deleteProveedor(): void {
        if (this.proveedor.id === 0) {
            this.onSaveComplete();
        } else {
            if (confirm(`Realmente desea eliminar el proveedor: ${this.proveedor.nombre}?`)) {
                this.proveedoService.deleteProveedor(this.proveedor.id)
                    .subscribe({
                        next: () => this.onSaveComplete(),
                        error: err => this.mensajeError = err
                    });
            }
        }
    }

    onSaveComplete(): void {
        this.proveedorForm.reset();
        this.router.navigate(['/proveedor']);
    }

    saveProveedor(): void {
        console.log("en el boton");
        
        if (this.proveedorForm.valid) {
            if (this.proveedorForm.dirty) {
                const p = { ...this.proveedor, ...this.proveedorForm.value };

                if (p.idProveedor === 0) {
                    this.proveedoService.createProveedor(p)
                        .subscribe({
                            next: () => this.onSaveComplete(),
                            error: err => this.mensajeError = err
                        });
                } else {
                    this.proveedoService.updateProveedor(p)
                        .subscribe({
                            next: () => this.onSaveComplete(),
                            error: err => this.mensajeError = err
                        });
                }
            } else {
                this.onSaveComplete();
            }
        } else {
            this.mensajeError = 'Verificar los errores de validacion'
        }
    }

    updateUSAmount(event) {
        // this.proveedorForm.get('telefono').setValue(event.target.value);
      }

}
import { Component, OnInit } from '@angular/core';
import { Proveedor } from '../../../models/settings/proveedor';
import { ProveedorService } from '../../../services/settings/proveedor.service';

@Component({
    templateUrl: 'proveedor.component.html'
})

export class ProveedorComponent implements OnInit {
    titulo: string = 'Lista de Proveedores'
    mensajeError: string;

    _filtrar = '';
    get filtrar(): string {
        return this._filtrar;
    }
    set filtrar(value: string) {
        this._filtrar = value;
        this.filtrados = this.filtrar ? this.performFilter(this.filtrar) : this.proveedores;
    }

    proveedores: Proveedor[]
    filtrados: Proveedor[]

    constructor(private investigadorService: ProveedorService) { }

    ngOnInit(): void {
        this.investigadorService.getProveedores().subscribe({
            next: proveedores => {
                this.proveedores = proveedores;;
                this.filtrados = this.proveedores;
            },
            error: err => this.mensajeError = err
        });

    }

    performFilter(filterBy: string): Proveedor[] {
        filterBy = filterBy.toLocaleLowerCase();
        return this.proveedores.filter((proveedores: Proveedor) =>
            proveedores.nombre.toLocaleLowerCase().indexOf(filterBy) !== -1);
    }
}
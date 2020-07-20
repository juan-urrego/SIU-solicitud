import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Solicitud } from '../models/solicitud';
import { SolicitudService } from '../services/solicitud.service';

@Component({
    templateUrl: 'solicitud.component.html'
})

export class SolicitudComponent implements OnInit {
    titulo: string = 'Lista de Solicitudes'
    mensajeError: string;

    _filtrar = '';
    get filtrar(): string {
        return this._filtrar;
    }
    set filtrar(value: string) {
        this._filtrar = value;
        this.filtrados = this.filtrar ? this.performFilter(this.filtrar) : this.solicitudes;
    }

    solicitudes: Solicitud[]
    filtrados: Solicitud[]

    constructor(private solicitudService: SolicitudService) { }

    ngOnInit(): void {
        this.solicitudService.getSolicitudes().subscribe({
            next: solicitudes => {
                this.solicitudes = solicitudes;;
                this.filtrados = this.solicitudes;
            },
            error: err => this.mensajeError = err
        });

    }

    performFilter(filterBy: string): Solicitud[] {
        filterBy = filterBy.toLocaleLowerCase();
        return this.solicitudes.filter((solicitudes: Solicitud) =>
            solicitudes.necesidad.toLocaleLowerCase().indexOf(filterBy) !== -1);
    }
}
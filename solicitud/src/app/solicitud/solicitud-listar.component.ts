import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

import { Solicitud } from './solicitud';
import { SolicitudService } from './solicitud.service';
import { EstudioService } from '../estudio/estudio.service';
import { ConsultaService } from '../consulta/consulta.service';
import { Consulta } from '../consulta/consulta';
import { Estudio } from '../estudio/estudio';

@Component({
    templateUrl: 'solicitud-listar.component.html'
})

export class SolicitudListarComponent implements OnInit {
    titulo: string = 'Lista de Solicitudes'
    mensajeError: string;
    consulta: Consulta;
    estudio: Estudio;
    form: FormGroup;
    update: FormGroup;
    solicitud: Solicitud;




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

    constructor(private solicitudService: SolicitudService,
        private estudioService: EstudioService,
        private consultaService: ConsultaService,
        private fb: FormBuilder) { }

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


    sendDocuments(id): void {
        this.solicitudService.crearDocumentos(id,null).subscribe({
            next: mensaje => {console.log(mensaje)},
            error: error => this.mensajeError = error
        });
    }

    downloadPdfs(): void {
        
    }

}

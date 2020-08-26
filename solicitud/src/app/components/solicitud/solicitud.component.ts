import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

import { Solicitud } from '../../models/solicitud';
import { SolicitudService } from '../../services/solicitud.service';
import { EstudioService } from '../../services/estudio.service';
import { ConsultaService } from '../../services/consulta.service';
import { Consulta } from '../../models/consulta';
import { Estudio } from '../../models/estudio';

@Component({
    templateUrl: 'solicitud.component.html'
})

export class SolicitudComponent implements OnInit {
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
        this.form = this.fb.group({
            id: 0,
            solicitud: this.fb.group({
                idSolicitud: id
            }),
            estado: 'Creada'
        });

        this.update = this.fb.group({
            idSolicitud:id,
            estado: "Documentos"
        });
        
        const p = { ...this.consulta, ...this.form.value };

        this.solicitudService.getSolicitud(id).subscribe({
            next: solicitud => {
                this.solicitud = { ...solicitud};
                this.solicitud.estado="Documentos";                
            },
            error: err => this.mensajeError=err
        })

        this.solicitudService.updateSolicitud(this.solicitud).subscribe({
            next: () => console.log("estado actualizado"),
            error: err => this.mensajeError = err            
        });

        this.consultaService.createConsulta(p).subscribe({
            next: () => console.log('consulta creada'),
            error: err => this.mensajeError = err
        });

        this.estudioService.createEstudio(p).subscribe({
            next: () => console.log('estudio creado'),
            error: err => this.mensajeError = err
        });
    }

}

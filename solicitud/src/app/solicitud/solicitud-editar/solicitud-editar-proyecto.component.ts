import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ParametroNecesidadService } from 'src/app/configuracion/parametro/necesidad/parametroNecesidad.service';
import { Proyecto } from 'src/app/configuracion/proyecto/proyecto';
import { Solicitud } from '../solicitud';

@Component({
    templateUrl: 'solicitud-editar-proyecto.component.html'
})

export class SolicitudEditarProyectoComponent implements OnInit {

    errorMessage: string;
    solicitud: Solicitud;
    solicitudForm: FormGroup;
    parametroNecesidad

    constructor(private route: ActivatedRoute,
                private parametroNecesidadService: ParametroNecesidadService 
        ) { }

    ngOnInit(): void {
        this.route.parent.data.subscribe(data => {            
            this.solicitud = data['resolvedData'].solicitud;
            this.solicitudForm = data['resolvedData'].form;
        });
        if(!this.solicitud){
            this.getParametrosNecesidades();
        }
    }

    displayProyecto(proyectoSelected: Proyecto) {
        this.solicitudForm.get('_codigo_proyecto').setValue(proyectoSelected.codigoProyecto);
        this.solicitudForm.get('_centro_costos_proyecto').setValue(proyectoSelected.centroCostos);        
    }

    getParametrosNecesidades(){
        return this.parametroNecesidadService.getParametroNecesidadActivo().subscribe({
            next: parametro => {                
                this.solicitudForm.get('necesidad').setValue(parametro.descripcion);
            },
            error: error => this.errorMessage = error
        });
    }
}
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LineaGeneral } from 'src/app/configuracion/linea-general/lineaGeneral';
import { LineaGeneralService } from 'src/app/configuracion/linea-general/lineaGeneral.service';
import { Solicitud } from '../solicitud';

@Component({
    templateUrl: 'solicitud-editar-detalle.component.html'
})

export class SolicitudEditarDetalleComponent implements OnInit {
    mensajeError: string;
    solicitudForm: FormGroup;
    solicitud: Solicitud;
    lineasGenerales: LineaGeneral[];
    
    get detalleTramiteDtos() {
        return this.solicitudForm.get('detalleTramiteDtos') as FormArray;
    }
    
    constructor(private route: ActivatedRoute,
        private fb: FormBuilder,
        private lineaGeneralService: LineaGeneralService) { }

    ngOnInit() {
        this.route.parent.data.subscribe(data => {
            this.solicitud = data['resolvedData'].solicitud;
            this.solicitudForm = data['resolvedData'].form;
        });
        this.getLineasGenerales();
    }

    displayLineasEspecificas(detalleSeleted : FormGroup) {
        if(detalleSeleted.controls['lineaGeneral'].value){
            detalleSeleted.controls['lineaEspecifica'].enable();            
        }
    }

    deleteDetails(index: number) {
        this.detalleTramiteDtos.removeAt(index);
        this.solicitudForm.markAsDirty();
    }

    addDetails(){
        this.detalleTramiteDtos.push(this.buildDetails());                
    }

    buildDetails() {
        return this.fb.group({
            lineaGeneral: ['', [Validators.required]],
            lineaEspecifica: [{value: '', disabled: true}, [Validators.required]],
            descripcion: ['', [Validators.required]],
            cantidad: ['', [Validators.required]]
        });
    }

    getLineasGenerales() {
        return this.lineaGeneralService.getLineaGenerales().subscribe({
            next: lineasGenerales => this.lineasGenerales = lineasGenerales,
            error: error => this.mensajeError = error
        })
    }
}
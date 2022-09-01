import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Consulta } from 'src/app/consulta/consulta';

@Component({

    templateUrl: 'consulta-editar.component.html'
})

export class ConsultaEditarComponent implements OnInit {

    consultaForm: FormGroup;
    consulta: Consulta;

    get detalleTramiteDtos(): FormArray {
        return this.consultaForm.get('_detalleTramiteDtos') as FormArray;
    }
    get precotizacionDtos(): FormArray {
        return this.consultaForm.get('_precotizacionDtos') as FormArray;
    }
    get argumentoDtos(): FormArray {
        return this.consultaForm.get('_argumentoDtos') as FormArray;
    }

    constructor(private route: ActivatedRoute) { }

    ngOnInit() {
        this.route.data.subscribe(data => {            
            this.consulta = data['resolvedData'].consulta;
            this.consultaForm = data['resolvedData'].form;
        });
    }
}
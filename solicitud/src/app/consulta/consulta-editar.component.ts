import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Consulta } from 'src/app/consulta/consulta';
import { ConsultaService } from 'src/app/consulta/consulta.service';
import { ParametroConsultaService } from '../configuracion/parametro/consulta/parametroConsulta.service';

@Component({

    templateUrl: 'consulta-editar.component.html'
})

export class ConsultaEditarComponent implements OnInit {
    mensajeError: string;
    title: string;

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

    constructor(private consultaService: ConsultaService,
        private parametroConsultaService: ParametroConsultaService,
        private router: Router,
        private route: ActivatedRoute) { }

    ngOnInit() {
        this.route.data.subscribe(data => {            
            this.consulta = data['resolvedData'].consulta;
            this.consultaForm = data['resolvedData'].form;
        });
        
        console.log(this.consulta.parametro);
        
    }


    onSaveComplete(): void {
        this.consultaForm.reset();
        this.router.navigate(['/consulta']);
    }


    send(): void {
        console.log(this.consultaForm.valid);
        console.log(this.consultaForm.dirty);
        
        
        if (this.consultaForm.valid) {

        } else {
            this.mensajeError = 'Verificar los errores de validacion'
        }
    }
}
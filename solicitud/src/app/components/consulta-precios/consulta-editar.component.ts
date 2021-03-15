import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Consulta } from 'src/app/models/consulta';
import { Grupo } from 'src/app/models/settings/grupo';
import { Investigador } from 'src/app/models/settings/investigador';
import { Precotizacion } from 'src/app/models/settings/precotizacion';
import { Solicitud } from 'src/app/models/solicitud';
import { ConsultaService } from 'src/app/services/consulta.service';

@Component({
    
    templateUrl: 'consulta-editar.component.html'
})

export class ConsultaEditarComponent implements OnInit {
    mensajeError: string;
    title: string;

    consultaForm: FormGroup;
    consulta: Consulta;
    grupo: Grupo;
    investigador: Investigador;
    solicitud: Solicitud;
    precotizacion: Precotizacion;

    valor: number;


    private sub: Subscription;

    get precotizaciones(): FormArray {
        return this.consultaForm.get('precotizaciones') as FormArray;
    }
    constructor(private consultaService: ConsultaService,
        private router: Router,
        private route: ActivatedRoute,
        private fb: FormBuilder) { }

    ngOnInit() {

        this.sub = this.route.paramMap.subscribe(
            params => {
                const id = +params.get('id');
                this.getConsulta(id);
            }
        )

        this.consultaForm = this.fb.group({
            acuerdo: '',

        })
     }

     getConsulta(id: number){
         this.consultaService.getConsulta(id).subscribe({
             next: (consulta: Consulta) => this.displayConsulta(consulta),
             error: err => this.mensajeError= err
         });
     }

     onSaveComplete(): void{
         this.consultaForm.reset();
         this.router.navigate(['/consulta']);
     }

    //  update(id){        
    //     let control = this.consulta.solicitud.precotizaciones;
    //     control.forEach(x => {        
    //         if(x.id == id){
    //             this.valor= x.valorTotal;
    //         }
    //     });
    //  }

     displayConsulta(consulta: Consulta): void {
         if(this.consultaForm){
             this.consultaForm.reset();
         }
         this.consulta = consulta;
         this.solicitud = consulta.solicitud;
        //  this.title = `Editar consulta: ${this.consulta.solicitud.nombreProyecto}`;         
         
        //  this.consultaForm.patchValue({
        //     acuerdo: this.consulta.,
        //     porque: this.consulta.porque

        // });
     }


     save(): void{

     }
}
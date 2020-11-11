import { Component, OnInit } from '@angular/core';
import { Form, FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Consulta } from 'src/app/models/consulta';
import { Grupo } from 'src/app/models/grupo';
import { Investigador } from 'src/app/models/investigador';
import { Precotizacion } from 'src/app/models/precotizacion';
import { Solicitud } from 'src/app/models/solicitud';
import { ConsultaService } from 'src/app/services/consulta.service';
import { SolicitudService } from 'src/app/services/solicitud.service';

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
        private fb: FormBuilder,
        private solicitudService: SolicitudService) { }

    ngOnInit() {

        this.sub = this.route.paramMap.subscribe(
            params => {
                const id = +params.get('id');
                this.getConsulta(id);
            }
        )

        this.consultaForm = this.fb.group({
            acuerdo: '',
            porque: '',
            solicitud: this.fb.group({
                idSolicitud: ''
            }),
            precotizacion: this.fb.group({
                idPrecotizacion: ''
            })

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

     update(id){        
        let control = this.consulta.solicitud.precotizaciones;
        control.forEach(x => {        
            if(x.idPrecotizacion == id){
                this.valor= x.valor;
            }
        });
     }

     displayConsulta(consulta: Consulta): void {
         if(this.consultaForm){
             this.consultaForm.reset();
         }
         this.consulta = consulta;
         this.solicitud = consulta.solicitud;
         if(this.consulta.precotizacion == null){
             this.consulta.precotizacion = {
                 idPrecotizacion: 0,
                 valor:0
             }

         }
         this.title = `Editar consulta: ${this.consulta.solicitud.nombreProyecto}`;         
         
         this.consultaForm.patchValue({
            acuerdo: this.consulta.acuerdo,
            porque: this.consulta.porque,
            solicitud:{
                idSolicitud: this.consulta.solicitud.idSolicitud
            },
            precotizacion: this.consulta.precotizacion

        });
     }


     save(): void{

     }
}
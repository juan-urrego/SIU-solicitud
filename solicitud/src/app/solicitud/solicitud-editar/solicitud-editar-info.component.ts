import { Component, OnInit, ViewChild } from "@angular/core";
import { FormGroup, NgForm } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { Grupo } from "src/app/configuracion/grupo/grupo";
import { GrupoService } from "src/app/configuracion/grupo/grupo.service";
import { Investigador } from "src/app/configuracion/investigador/investigador";
import { InvestigadorService } from "src/app/configuracion/investigador/investigador.service";
import { Solicitud } from "../solicitud";

@Component({
    templateUrl: './solicitud-editar-info.component.html'
  })
  export class SolicitudEditarInfoComponent implements OnInit {
  
    errorMessage: string;
    solicitud: Solicitud;
    solicitudForm: FormGroup;
    tipoTramites = ['Nacional', 'Internacional'];
    grupos: Grupo[];
    investigadores: Investigador[];
    
  
    constructor(private route: ActivatedRoute,
                private router: Router,
                private investigadorService: InvestigadorService,
                private grupoService: GrupoService) { }
  
    ngOnInit(): void {
      this.route.parent.data.subscribe(data => {
        console.log(data);
        
        this.solicitud = data['resolvedData'].solicitud;
        this.solicitudForm = data['resolvedData'].form;
      });
      this.getGrupos();
      this.getInvestigadores();
    }

    getInvestigadores() {
      return this.investigadorService.getInvestigadores().subscribe({
          next: investigadores => this.investigadores = investigadores,
          error: error => this.errorMessage = error
      })
    }

    getGrupos() {
      return this.grupoService.getGrupos().subscribe({
          next: grupos => this.grupos = grupos,
          error: error => this.errorMessage = error
      })
    }

    displayInvestigador(investigadorSelected: Investigador) {      
      this.solicitudForm.get('_identificacion_investigador').setValue(investigadorSelected.identificacion);
    }


    displayProyectos() {      
      this.solicitudForm.get('proyecto').enable();
    }

    nextPage() {
      this.router.navigate(['./editar/proyecto']);
    }
}
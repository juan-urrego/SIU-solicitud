import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { GrupoService } from '../services/grupo.service';
import { Grupo } from '../models/grupo';
import { InvestigadorService } from '../services/investigador.service';
import { Investigador } from '../models/investigador';
import { FormArray, FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
    templateUrl: 'grupo-editar.component.html'
})

export class GrupoEditarComponent implements OnInit {
    title = '';
    investigadors: Investigador[];
    grupo: Grupo | undefined;
    mensajeError: string;
    grupoForm: FormGroup;
    private sub: Subscription;

    get investigadores(): FormArray {
        return this.grupoForm.get('investigadores') as FormArray;
    }


    constructor(private grupoService: GrupoService,
        private investigadorService: InvestigadorService,
        private router: Router,
        private route: ActivatedRoute,
        private fb: FormBuilder) { }

    ngOnInit() {
        this.getInvestigadores()
        
        
        this.grupoForm = this.fb.group({            
            idGrupo: 1,
            nombre: ['', [Validators.required, Validators.minLength(3)]],
            // idGrupo:[ '',[Validators.required]],
            codCol: ['', [Validators.required]],
            investigadores: this.fb.array([])
        });

        this.sub = this.route.paramMap.subscribe(
            params => {
                const id = +params.get('id');            
                this.getGrupo(id);
            }
        );
        

        console.log(this.investigadores.controls);
        

    }
    addAddresses(): void {
        this.investigadores.push(this.buildAddress());
    }

    displayGrupo(grupo: Grupo): void {
        if (this.grupoForm) {
            this.grupoForm.reset();
        }
        this.grupo = grupo;

        if (this.grupo.idGrupo === 1) {
            this.title = "Agregar Grupo";
        } else {
            this.title = `Editar Grupo: ${this.grupo.nombre}`;
        }

        this.grupoForm.patchValue({   
            idGrupo: this.grupo.idGrupo,         
            nombre: this.grupo.nombre,
            codCol: this.grupo.codCol,
        });
        this.setInvestigadores();

    }

    setInvestigadores(){
        let control = <FormArray>this.grupoForm.controls.investigadores;
        this.grupo.investigadores.forEach(x => {
            control.push(this.fb.group(x));
        });
    }
    


    getGrupo(id: number) {
        this.grupoService.getGrupo(id)
            .subscribe({
                next: (grupo: Grupo) => this.displayGrupo(grupo),
                error: err => this.mensajeError = err
            });
    }

    getInvestigadores() {
        return this.investigadorService.getInvestigadores().subscribe({
            next: investigadores => {
                this.investigadors = investigadores;;
            },
            error: err => this.mensajeError = err
        });
    }

    deleteGrupo(): void {
        if (this.grupo.idGrupo === 1) {
            this.onSaveComplete();
        } else {
            if (confirm(`Realmente desea eliminar el grupo: ${this.grupo.nombre}?`)) {
                this.grupoService.deleteGrupo(this.grupo.idGrupo)
                    .subscribe({
                        next: () => this.onSaveComplete(),
                        error: err => this.mensajeError = err
                    });
            }
        }
        this.onSaveComplete();
    }

    onSaveComplete(): void {
        this.grupoForm.reset();
        this.router.navigate(['/grupo']);
    }

    onBack(): void {
        this.router.navigate(['/grupo']);
    }

    buildAddress(): FormGroup {
        return this.fb.group({
            idInvestigador: ''
        })
    }

    saveGrupo(): void {
        console.log("en el boton");

        if (this.grupoForm.valid) {
            if (this.grupoForm.dirty) {
                const p = { ...this.grupo, ...this.grupoForm.value };

                if (this.grupo.idGrupo === 1) {
                    console.log(p);
                    console.log('grupo' +  this.grupoForm.value);
                    
                    
                    this.grupoService.createGrupo(p)
                        .subscribe({
                            next: () => this.onSaveComplete(),
                            error: err => this.mensajeError = err
                        });
                } else {
                    this.grupoService.updateGrupo(p)
                        .subscribe({
                            next: () => this.onSaveComplete(),
                            error: err => this.mensajeError = err
                        });
                }
            } else {
                this.onSaveComplete();
            }
        } else {
            this.mensajeError = 'Verificar los errores de validacion'
        }
    }


}
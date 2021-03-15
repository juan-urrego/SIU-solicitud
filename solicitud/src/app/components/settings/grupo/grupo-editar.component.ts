import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { GrupoService } from '../../../services/settings/grupo.service';
import { Grupo } from '../../../models/settings/grupo';
import { FormArray, FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
    templateUrl: 'grupo-editar.component.html'
})

export class GrupoEditarComponent implements OnInit {
    title = '';
    grupo: Grupo | undefined;
    mensajeError: string;
    grupoForm: FormGroup;
    private sub: Subscription;

    get investigadores(): FormArray {
        return this.grupoForm.get('investigadores') as FormArray;
    }


    constructor(private grupoService: GrupoService,
        private router: Router,
        private route: ActivatedRoute,
        private fb: FormBuilder) { }

    ngOnInit() {
        
        
        this.grupoForm = this.fb.group({            
            idGrupo: 1,
            nombre: ['', [Validators.required, Validators.minLength(3)]],
            codCol: ['', [Validators.required]]
        });

        this.sub = this.route.paramMap.subscribe(
            params => {
                const id = +params.get('id');            
                this.getGrupo(id);
            }
        );
        

        console.log(this.investigadores.controls);
        

    }

    displayGrupo(grupo: Grupo): void {
        if (this.grupoForm) {
            this.grupoForm.reset();
        }
        this.grupo = grupo;

        if (this.grupo.id === 1) {
            this.title = "Agregar Grupo";
        } else {
            this.title = `Editar Grupo: ${this.grupo.nombre}`;
        }

        this.grupoForm.patchValue({   
            idGrupo: this.grupo.id,         
            nombre: this.grupo.nombre,
            codCol: this.grupo.codColciencia,
        });

    }


    getGrupo(id: number) {
        this.grupoService.getGrupo(id)
            .subscribe({
                next: (grupo: Grupo) => this.displayGrupo(grupo),
                error: err => this.mensajeError = err
            });
    }


    deleteGrupo(): void {
        if (this.grupo.id === 1) {
            this.onSaveComplete();
        } else {
            if (confirm(`Realmente desea eliminar el grupo: ${this.grupo.nombre}?`)) {
                this.grupoService.deleteGrupo(this.grupo.id)
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


    saveGrupo(): void {
        console.log("en el boton");

        if (this.grupoForm.valid) {
            if (this.grupoForm.dirty) {
                const p = { ...this.grupo, ...this.grupoForm.value };

                if (this.grupo.id === 1) {
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
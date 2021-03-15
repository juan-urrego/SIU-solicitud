import { Component, OnInit } from '@angular/core';
import { Investigador } from '../../../models/settings/investigador';

import { InvestigadorService } from '../../../services/settings/investigador.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
    templateUrl: 'investigador-editar.component.html'
})

export class InvestigadorEditarComponent implements OnInit {
    title = '';
    investigador: Investigador;    
    mensajeError: string;
    investigadorForm: FormGroup;
    private sub: Subscription;


    constructor(
        private investigadorService: InvestigadorService,
        private router: Router,
        private route: ActivatedRoute,
        private fb: FormBuilder) { }

    ngOnInit() { 
        this.investigadorForm = this.fb.group({            
            nombre: ['', [Validators.required, Validators.minLength(3)]],
            identificacion:[ '',[Validators.required]],
            telefono: ['', [Validators.required]],           
            email: ['', [Validators.required]]           
        });

        this.sub = this.route.paramMap.subscribe(
            params => {
                const id = +params.get('id');            
                this.getInvestigador(id);
            }
        );
        

    }

    getInvestigador(id: number) {
        this.investigadorService.getInvestigador(id).subscribe({
            next: (investigador: Investigador) => this.displayInvestigador(investigador),
            error: err => this.mensajeError = err
        });
    }

    deleteInvestigador(): void {
        if (this.investigador.id === 0) {
            this.onSaveComplete();
        } else {
            if (confirm(`Realmente desea eliminar el investigador: ${this.investigador.nombre}?`)) {
                this.investigadorService.deleteInvestigador(this.investigador.id)
                    .subscribe({
                        next: () => this.onSaveComplete(),
                        error: err => this.mensajeError = err
                    });
            }
        }
        this.onSaveComplete();
    }

    onSaveComplete(): void {
        this.investigadorForm.reset();
        this.router.navigate(['/investigador']);
    }


    displayInvestigador(investigador: Investigador): void {
        if (this.investigadorForm) {
            this.investigadorForm.reset();
        }
        this.investigador = investigador;

        if (this.investigador.id === 0) {
            this.title = "Agregar Investigador";
        } else {
            this.title = `Editar Proveedor: ${this.investigador.nombre}`;
        }

        this.investigadorForm.patchValue({            
            nombre: this.investigador.nombre,
            telefono: this.investigador.telefono,
            identificacion: this.investigador.identificacion,
            email: this.investigador.email
        });
        

    }

    onBack(): void {
        this.router.navigate(['/grupo']);
    }

    saveInvestigador(): void {
        console.log("en el boton");

        if (this.investigadorForm.valid) {
            if (this.investigadorForm.dirty) {
                const p = { ...this.investigador, ...this.investigadorForm.value };

                if (this.investigador.id === 0) {
                    console.log(p);
                    console.log('investigador' +  this.investigadorForm.value);
                    
                    
                    // this.investigadorService.createInvestigador(...this.investigadorForm.value, files)
                    //     .subscribe({
                    //         next: () => this.onSaveComplete(),
                    //         error: err => this.mensajeError = err
                    //     });
                } else {
                    // this.investigadorService.updateInvestigador(p)
                    //     .subscribe({
                    //         next: () => this.onSaveComplete(),
                    //         error: err => this.mensajeError = err
                    //     });
                }
            } else {
                this.onSaveComplete();
            }
        } else {
            this.mensajeError = 'Verificar los errores de validacion'
        }
    }
}
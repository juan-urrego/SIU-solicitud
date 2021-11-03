import { Injectable } from "@angular/core";
import { FormArray, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { Observable, of } from "rxjs";
import { map } from "rxjs/operators";
import { EstudiioResolved, Estudio } from "./estudio";
import { EstudioService } from "./estudio.service";

@Injectable({ providedIn: 'root' })
export class EstudioResolver implements Resolve<EstudiioResolved> {

    estudioForm: FormGroup;

    get detalleTramiteDtos(): FormArray {
        return this.estudioForm.get('_detalleTramiteDtos') as FormArray;
    }


    constructor(private fb: FormBuilder,
                private estudioService: EstudioService) { }

    resolve(route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<EstudiioResolved> {
        const id = route.paramMap.get('id');
        this.estudioForm = this.fb.group({
            unidadAcademica: ['', [Validators.required]],
            firmaUsuario: false,
            firmaDirector: false,
            _director: { value: '', disabled: true },
            _usuario: { value: '', disabled: true },
            _acuerdo: { value: '', disabled: true },
            _fecha: { value: '', disabled: true },
            _centroCostos: { value: '', disabled: true },
            _necesidad: { value: '', disabled: true },
            _detalleTramiteDtos: this.fb.array([]),
            _valor: { value: '', disabled: true },
            _verificacion: { value: '', disabled: true },
        });

        if (isNaN(+id)) {
            const message = `Estudio id was not a number: ${id}`;
            console.error(message);
            return of({ estudio: null, error: message, form: this.estudioForm });
        }
        
        return this.estudioService.getEstudio(+id)
            .pipe(
                map(estudio => ({ estudio: estudio, form: this.displayForm(estudio) }))
            );
    }

    displayForm(estudio: Estudio): FormGroup {
        estudio.solicitud.detalleTramites.forEach(detalle => {
            let newDetail = this.buildDetails();
            newDetail.patchValue({                
                _lineaEspecifica: detalle.lineaEspecifica.nombre,
                _lineaGeneral: detalle.lineaGeneral.nombre,
                _descripcion: detalle.descripcion,
                _cantidad: detalle.cantidad
            });        
            this.detalleTramiteDtos.push(newDetail);
        });

        this.estudioForm.patchValue({
            unidadAcademica: estudio.unidadAcademica,
            _director: estudio.director.name,
            _usuario: estudio.solicitud.user.name,
            _acuerdo: estudio.acuerdo,
            _fecha: new Date(estudio.solicitud.fecha),
             _centroCostos: estudio.solicitud.grupoInvestigador.proyecto.centroCostos,
            _necesidad: estudio.solicitud.necesidad,
            _valor: estudio.solicitud.valor,
            _verificacion: estudio.solicitud.verificacion
        })
        return this.estudioForm;
    }

    buildDetails() {
        return this.fb.group({
            _lineaGeneral: { value: '', disabled: true },
            _lineaEspecifica: {value: '', disabled: true},
            _descripcion: { value: '', disabled: true },
            _cantidad: { value: '', disabled: true }
        });
    }

    addDetails(){
        this.detalleTramiteDtos.push(this.buildDetails());                
    }

}
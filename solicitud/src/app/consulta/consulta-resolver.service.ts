import { Injectable } from "@angular/core";
import { FormArray, FormBuilder, FormGroup } from "@angular/forms";
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { Observable, of } from "rxjs";
import { map } from "rxjs/operators";
import { ConsultaResolved } from "./consulta";
import { ConsultaService } from "./consulta.service";

@Injectable({ providedIn: 'root' })
export class ConsultaResolver implements Resolve<ConsultaResolved> {

    consultaForm: FormGroup;

    get detalleTramiteDtos(): FormArray {
        return this.consultaForm.get('_detalleTramiteDtos') as FormArray;
    }
    get precotizacionDtos(): FormArray {
        return this.consultaForm.get('_precotizacionDtos') as FormArray;
    }
    get argumentoDtos(): FormArray {
        return this.consultaForm.get('_argumentoDtos') as FormArray;
    }

    constructor(private fb: FormBuilder,
                private consultaService: ConsultaService) { }

    resolve(route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<ConsultaResolved> {
        const id = route.paramMap.get('id');
        this.consultaForm = this.fb.group({
            parametro: '',
            _fecha: { value: '', disabled: true },
            _detalleTramiteDtos: this.fb.array([]),
            _precotizacionDtos: this.fb.array([]),
            _precotizacionDto: { value: '', disabled: true },
            _valorElegido: { value: '', disabled: true },
            _argumentoDtos: this.fb.array([]),
            _observacion: { value: '', disabled: true },
            _grupo: { value: '', disabled: true },
            _investigador: { value: '', disabled: true },
            _identificacion_investigador: { value: '', disabled: true },
            _telefono_investigador: { value: '', disabled: true }
        });

        if (isNaN(+id)) {
            const message = `Consulta id was not a number: ${id}`;
            console.error(message);
            return of({ consulta: null, error: message, form: this.consultaForm });
        }
        
        return this.consultaService.getConsulta(+id)
            .pipe(
                map(consulta => ({ consulta: consulta, form: this.displayForm(consulta) }))
            );
    }

    displayForm(consulta): FormGroup {
        consulta.solicitud.detalleTramites.forEach(detalle => {
            let newDetail = this.buildDetails();
            newDetail.patchValue({                
                _lineaEspecifica: detalle.lineaEspecifica.nombre,
                _lineaGeneral: detalle.lineaGeneral.nombre,
                _descripcion: detalle.descripcion,
                _cantidad: detalle.cantidad
            });        
            this.detalleTramiteDtos.push(newDetail);
        });

        consulta.solicitud.precotizaciones.forEach(precotizacion => {
            let newPrecotizacion = this.buildPrecotizacion();
            newPrecotizacion.patchValue({
                _proveedor: precotizacion.proveedor.nombre,
                _identificacion_proveedor: precotizacion.proveedor.identificacion,
                _telefono_proveedor: precotizacion.proveedor.telefono,
                _ciudad_proveedor: precotizacion.proveedor.ciudad,
                _valorTotal: precotizacion.valorTotal,
                _valorIva: precotizacion.valorIva  
            });
            this.precotizacionDtos.push(newPrecotizacion);
        });

        consulta.solicitud.precotizacionElegida.argumentos.forEach(argumento => {
            let newArgumento = this.buildArgumentos();
            newArgumento.patchValue({
                _descripcion: argumento.descripcion
            });
            this.argumentoDtos.push(newArgumento);
        });

        this.consultaForm.patchValue({
            parametro: consulta.parametro,
            _fecha: new Date(consulta.solicitud.fecha),
            _precotizacionDto: consulta.solicitud.precotizacionElegida.proveedor.nombre,
            _valorElegido: consulta.solicitud.precotizacionElegida.valorTotal,
            _observacion: consulta.solicitud.observacion,
            _grupo: consulta.solicitud.grupoInvestigador.grupo.nombre,
            _investigador: consulta.solicitud.grupoInvestigador.investigador.nombre,
            _identificacion_investigador: consulta.solicitud.grupoInvestigador.investigador.identificacion,
            _telefono_investigador: consulta.solicitud.grupoInvestigador.investigador.telefono
        })
        return this.consultaForm;
    }

    buildDetails() {
        return this.fb.group({
            _lineaGeneral: { value: '', disabled: true },
            _lineaEspecifica: {value: '', disabled: true},
            _descripcion: { value: '', disabled: true },
            _cantidad: { value: '', disabled: true }
        });
    }

    buildArgumentos() {
        return this.fb.group({
            _descripcion: { value: '', disabled: true }
        });
    }

    buildPrecotizacion(): FormGroup {
        return this.fb.group({            
            _proveedor: { value: '', disabled: true },
            _identificacion_proveedor: {value: '', disabled: true},
            _telefono_proveedor: {value: '', disabled: true},
            _ciudad_proveedor: {value: '', disabled: true},
            _valorTotal: { value: '', disabled: true },
            _valorIva: { value: '', disabled: true }            
        });
    }

    addArgumento() {
        this.argumentoDtos.push(this.buildArgumentos());
    }

    addPrecotizacion(): void {        
        this.precotizacionDtos.push(this.buildPrecotizacion());
    }

    addDetails(){
        this.detalleTramiteDtos.push(this.buildDetails());                
    }

}
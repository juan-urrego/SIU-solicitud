import { Injectable } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Solicitud, SolicitudResolved } from './solicitud';
import { SolicitudService } from './solicitud.service';

@Injectable({providedIn: 'root'})
export class SolicitudResolver implements Resolve<SolicitudResolved> {
    
    solicitudForm: FormGroup;

    get detalleTramiteDtos(): FormArray {
        return this.solicitudForm.get('detalleTramiteDtos') as FormArray;
    }
    get precotizacionDtos(): FormArray {
        return this.solicitudForm.get('precotizacionDtos') as FormArray;
    }
    get argumentoDtos(): FormArray {
        return this.solicitudForm.get('argumentoDtos') as FormArray;
    }

    
    constructor(private solicitudService: SolicitudService,
                private fb: FormBuilder) {

    }


    resolve( route: ActivatedRouteSnapshot, 
             state: RouterStateSnapshot): Observable<SolicitudResolved> {
        const id = route.paramMap.get('id');
        this.solicitudForm = this.fb.group({
            tipoTramite: ['', [Validators.required]],
            grupo: ['', [Validators.required]],
            investigador: ['', [Validators.required]],
            _identificacion_investigador: {value: '', disabled: true},
            nombreContacto: ['', [Validators.required]],
            telefonoContacto: ['', [Validators.required]],
            cargo: ['', [Validators.required]],
            necesidad: [''],
            proyecto: [{value: '', disabled: true}, [Validators.required]],
            _codigo_proyecto: {value: '', disabled: true},
            _centro_costos_proyecto: {value: '', disabled: true},
            fecha: [new Date(), [Validators.required]],            
            detalleTramiteDtos: this.fb.array([]),
            valor: [null, [Validators.required]],
            verificacion: ['si', [Validators.required]],
            precotizacionDtos: this.fb.array([]),
            precotizacionDto: ['', [Validators.required]],
            _valor_selected: {value: '', disabled: true},     
            argumentoDtos: this.fb.array([]),
            observacion: '',
            usuarioEmail: ''
        });

        if (isNaN(+id)) {
            const message = `Product id was not a number: ${id}`;
            console.error(message);
            return of({solicitud: null, error: message, form: this.solicitudForm});
        }

        if (+id == 0) {
            this.addDetails();
            this.addPrecotizacion();
            this.addArgumento();
            return of({solicitud: null , form: this.solicitudForm});
        }
        return this.solicitudService.getSolicitud(+id)
            .pipe(
                map(solicitud => ({solicitud: solicitud, form: this.displayForm(solicitud)}))
            );
    }

    displayForm(solicitud: Solicitud): FormGroup {
        if (solicitud.grupoInvestigador.proyecto) {            
            this.displayProyectos();      
        }
        solicitud.detalleTramites.forEach(detalle => {
            let newDetail = this.buildDetails();
            newDetail.get('lineaGeneral').setValue(detalle.lineaGeneral)
            this.displayLineasEspecificas(newDetail);
            newDetail.patchValue({                
                lineaEspecifica: detalle.lineaEspecifica,
                descripcion: detalle.descripcion,
                cantidad: detalle.cantidad
            });        
            this.detalleTramiteDtos.push(newDetail);
        });

        solicitud.precotizaciones.forEach(precotizacion => {
            let newPrecotizacion = this.buildPrecotizacion();
            newPrecotizacion.patchValue({
                proveedor: precotizacion.proveedor,
                _identificacion_proveedor: precotizacion.proveedor.identificacion,
                _telefono_proveedor: precotizacion.proveedor.telefono,
                _ciudad_proveedor: precotizacion.proveedor.ciudad,
                valorTotal: precotizacion.valorTotal,
                valorIva: precotizacion.valorIva  
            });
            this.precotizacionDtos.push(newPrecotizacion);
        });

        solicitud.precotizacionElegida.argumentos.forEach(argumento => {
            let newArgumento = this.buildArgumentos();
            newArgumento.patchValue({
                descripcion: argumento.descripcion
            });
            this.argumentoDtos.push(newArgumento);
        });

        this.solicitudForm.patchValue({
            tipoTramite: solicitud.tipoTramite,
            grupo: solicitud.grupoInvestigador.grupo,
            investigador: solicitud.grupoInvestigador.investigador,
            _identificacion_investigador: solicitud.grupoInvestigador.investigador.identificacion,
            nombreContacto: solicitud.grupoInvestigador.nombreContacto,
            telefonoContacto: solicitud.grupoInvestigador.telefonoContacto,
            cargo: solicitud.grupoInvestigador.cargo,
            necesidad: solicitud.necesidad,
            proyecto: solicitud.grupoInvestigador.proyecto,
            _codigo_proyecto: solicitud.grupoInvestigador.proyecto.codigoProyecto,
            _centro_costos_proyecto: solicitud.grupoInvestigador.proyecto.centroCostos,            
            fecha: new Date(solicitud.fecha),
            valor: solicitud.valor,
            verificacion: solicitud.verificacion,            
            precotizacionDto: solicitud.precotizacionElegida,
            _valor_selected: solicitud.precotizacionElegida.valorTotal,                        
            observacion: solicitud.observacion,
            usuarioEmail: solicitud.user.email
        });
        
        
        return this.solicitudForm;
    }

    buildDetails() {
        return this.fb.group({
            lineaGeneral: ['', [Validators.required]],
            lineaEspecifica: [{value: '', disabled: true}, [Validators.required]],
            descripcion: ['', [Validators.required]],
            cantidad: ['', [Validators.required]]
        });
    }

    buildArgumentos() {
        return this.fb.group({
            descripcion: ['', [Validators.required]]
        });
    }

    buildPrecotizacion(): FormGroup {
        return this.fb.group({            
            proveedor: ['', [Validators.required]],
            _identificacion_proveedor: {value: '', disabled: true},
            _telefono_proveedor: {value: '', disabled: true},
            _ciudad_proveedor: {value: '', disabled: true},
            valorTotal: [null, [Validators.required]],
            valorIva: [null, [Validators.required]]            
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

    displayLineasEspecificas(detalleSeleted : FormGroup) {
        if(detalleSeleted.controls['lineaGeneral'].value){
            detalleSeleted.controls['lineaEspecifica'].enable();            
        }
    }

    displayProyectos() {
        this.solicitudForm.get('proyecto').enable();
    }
}
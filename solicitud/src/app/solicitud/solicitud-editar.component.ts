import { Component, OnInit, OnDestroy } from '@angular/core';
import { Solicitud } from './solicitud';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { SolicitudService } from './solicitud.service';
import { GrupoService } from '../configuracion/grupo/grupo.service';
import { Grupo } from '../configuracion/grupo/grupo';
import { Proveedor } from '../configuracion/proveedor/proveedor';
import { ProveedorService } from '../configuracion/proveedor/proveedor.service';
import { InvestigadorService } from '../configuracion/investigador/investigador.service';
import { Investigador } from '../configuracion/investigador/investigador';
import { ProyectoService } from '../configuracion/proyecto/proyecto.service';
import { Proyecto } from '../configuracion/proyecto/proyecto';
import { ParametroNecesidadService } from '../configuracion/parametro/necesidad/parametroNecesidad.service';
import { Parametro } from '../configuracion/parametro/parametro';
import { LineaGeneralService } from '../configuracion/linea-general/lineaGeneral.service';
import { LineaGeneral } from '../configuracion/linea-general/lineaGeneral';
import { lineaEspecifica } from '../configuracion/linea-general/lineaEspecifica';
import { ParametroArgumentoService } from '../configuracion/parametro/argumento/parametroArgumento.service';
import { ParametroObservacionService } from '../configuracion/parametro/observacion/parametroObservacion.service';
import { DetalleTramite } from './detalleTramite';

@Component({
    templateUrl: 'solicitud-editar.component.html',
    styleUrls: ['solicitud-editar.component.scss']
})

export class SolicitudEditarComponent implements OnInit, OnDestroy {
    title = '';
    mensajeError: string;
    agregarSolicitud: boolean;
    proveedorExisting = false;    

    solicitudForm: FormGroup;
    solicitud: Solicitud;
    solicitudSelected: Solicitud;
    grupo: Grupo;
    grupos: Grupo[];
    investigador: Investigador;
    investigadores: Investigador[];
    proveedores: Proveedor[];
    proyectos: Proyecto[];
    argumentos: Parametro[];
    lineasGenerales: LineaGeneral[];
    proveedor: Proveedor;
    grupoSelected: any = {};
    proyectoSelected: any = {
        centroCostos: '',
        codigoProyecto: ''        
    };
    lineasEspecificas: lineaEspecifica[];
    lineaSelected: any = {};
    investigadorSelected: any = {identificacion: ''};
    parametroNecesidad: any = {};
    parametroObservacion: any = {};
    isNew = false;
    
    tipoTramites = ['Nacional', 'Internacional'];

    get detalleTramiteDtos() {
        return this.solicitudForm.get('detalleTramiteDtos') as FormArray;
    }
    get precotizacionDtos(): FormArray {
        return this.solicitudForm.get('precotizacionDtos') as FormArray;
    }
    get argumentoDtos(): FormArray {
        return this.solicitudForm.get('argumentoDtos') as FormArray;
    }
    

    private sub: Subscription;



    constructor(private solicitudService: SolicitudService,
        private router: Router,
        private route: ActivatedRoute,
        private fb: FormBuilder,
        private grupoService: GrupoService,
        private proveedorService: ProveedorService,
        private investigadorService: InvestigadorService,
        private parametroNecesidadService: ParametroNecesidadService,
        private lineaGeneralService: LineaGeneralService,
        private parametroArgumentoService: ParametroArgumentoService,
        private parametroObservacionService: ParametroObservacionService) { }

    ngOnInit() {        
        this.getGrupos();
        this.getProveedores();
        this.getInvestigadores();
        this.getParametroNecesidad();
        this.getLineaGeneral();
        this.getParametroArgumento();
        this.getParametroObservacion();
        
        this.sub = this.route.paramMap.subscribe(
            params => {
                const id = +params.get('id');
                if (id === 0) {
                    this.agregarSolicitud = true;
                } else {
                    this.getSolicitud(id);
                }
            }
        );


        this.solicitudForm = this.fb.group({
            tipoTramite: '',
            grupo: '',
            investigador: '',
            _identificacion_investigador: {value: '', disabled: true},
            nombreContacto: '',
            telefonoContacto: '',
            cargo: '',
            necesidad: '',
            proyecto: {value: '', disabled: true},
            _codigo_proyecto: {value: '', disabled: true},
            _centro_costos_proyecto: {value: '', disabled: true},
            fecha: [new Date(), [Validators.required]],            
            detalleTramiteDtos: this.fb.array([]),
            valor: null,
            verificacion: 'si',
            precotizacionDtos: this.fb.array([]),
            precotizacionDto: '',
            _valor_selected: {value: '', disabled: true},            
            argumentoDtos: this.fb.array([]),            
            observacion: ['', [Validators.required]]
        });

        if (this.agregarSolicitud){
            this.addDetails();
            this.addPrecotizacion();
            this.addArgumento();
        }

    }

    ngOnDestroy(): void {
        this.sub.unsubscribe();
    }

    buildDetails() {
        return this.fb.group({
            lineaGeneral: '',
            lineaEspecifica: {value: '', disabled: true},
            descripcion: '',
            cantidad: ''
        });
    }

    addDetails(){
        this.detalleTramiteDtos.push(this.buildDetails());                
    }

    deleteDetails(index: number) {
        this.detalleTramiteDtos.removeAt(index);
        this.solicitudForm.markAsDirty();
    }

    buildPrecotizacion(): FormGroup {
        return this.fb.group({            
            proveedor: '',
            _identificacion_proveedor: {value: '', disabled: true},
            _telefono_proveedor: {value: '', disabled: true},
            _ciudad_proveedor: {value: '', disabled: true},
            valorTotal: null,
            valorIva: null            
        });
    }

    addPrecotizacion(): void {        
        this.precotizacionDtos.push(this.buildPrecotizacion());
    }

    deletePrecotizacion(index: number) {
        this.precotizacionDtos.removeAt(index);
        this.precotizacionDtos.markAsDirty();
    }

    buildArgumentos() {
        return this.fb.group({
            descripcion: ''
        });
    }

    addArgumento() {
        this.argumentoDtos.push(this.buildArgumentos());
    }

    deleteArgumento(index: number) {
        this.argumentoDtos.removeAt(index);
        this.solicitudForm.markAsDirty();
    }

    displaySolicitud(solicitud: Solicitud): void {
        
        this.solicitud = solicitud;
        console.log(this.solicitud);                
        this.title = `Editar solicitud: ${this.solicitud.id}`;
        
        
        this.parametroNecesidad.descripcion = this.solicitud.necesidad;        
            
        if (this.solicitud.grupoInvestigador.proyecto) {            
            this.displayProyectos();      
        }
        this.solicitud.detalleTramites.forEach(detalle => {
            let newDetail = this.buildDetails();
            newDetail.get('lineaGeneral').setValue(detalle.lineaGeneral)
            this.displayLineasEspecificas(newDetail);
            newDetail.patchValue({                
                lineaEspecifica: detalle.lineaEspecifica,
                descripcion: detalle.descripcion,
                cantidad: detalle.cantidad
            });
            console.log(newDetail.get('lineaEspecifica').value);            
            console.log(detalle.lineaEspecifica);            
            this.detalleTramiteDtos.push(newDetail);
        });

        this.solicitud.precotizaciones.forEach(precotizacion => {
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

        

        this.solicitud.precotizacionElegida.argumentos.forEach(argumento => {
            let newArgumento = this.buildArgumentos();
            newArgumento.patchValue({
                descripcion: argumento.descripcion
            });
            this.argumentoDtos.push(newArgumento);
        });
        
        

        this.solicitudForm.patchValue({
            tipoTramite: this.solicitud.tipoTramite,
            grupo: this.solicitud.grupoInvestigador.grupo,
            investigador: this.solicitud.grupoInvestigador.investigador,
            _identificacion_investigador: this.solicitud.grupoInvestigador.investigador.identificacion,
            nombreContacto: this.solicitud.grupoInvestigador.nombreContacto,
            telefonoContacto: this.solicitud.grupoInvestigador.telefonoContacto,
            cargo: this.solicitud.grupoInvestigador.cargo,
            necesidad: this.solicitud.necesidad,
            proyecto: this.solicitud.grupoInvestigador.proyecto,
            _codigo_proyecto: this.solicitud.grupoInvestigador.proyecto.codigoProyecto,
            _centro_costos_proyecto: this.solicitud.grupoInvestigador.proyecto.centroCostos,            
            valor: this.solicitud.valor,
            verificacion: this.solicitud.verificacion,            
            precotizacionDto: this.solicitud.precotizacionElegida,
            _valor_selected: this.solicitud.precotizacionElegida.valorTotal,                        
            observacion: this.solicitud.observacion
        });
                        
    }

    getSolicitud(id: number) {
        this.solicitudService.getSolicitud(id).subscribe({
            next: (solicitud: Solicitud) => this.displaySolicitud(solicitud),
            error: err => this.mensajeError = err
        });
    }

    deleteSolicitud(): void {
        if (this.solicitud.id === 0) {
            this.onSaveComplete();
        } else {
            if (confirm(`Realmente desea eliminar la solicitud: ${this.solicitud.tipoTramite}?`)) {
                this.solicitudService.deleteSolicitud(this.solicitud.id)
                    .subscribe({
                        next: () => this.onSaveComplete(),
                        error: err => this.mensajeError = err
                    });
            }
        }
    }

    onSaveComplete(): void {
        this.solicitudForm.reset();
        this.router.navigate(['/solicitud']);
    }

    saveSolicitud(): void {

        if (this.solicitudForm.valid) {
            console.log("valid");

            if (this.solicitudForm.dirty) {
                console.log("dirty");
                console.log(this.solicitud);
                

                const p = { ...this.solicitud, ...this.solicitudForm.value };

                if (this.agregarSolicitud) {
                    console.log(p);
                    console.log('solicitud' + this.solicitudForm.value);


                    this.solicitudService.createSolicitud(p)
                        .subscribe({
                            next: () => this.onSaveComplete(),
                            error: err => this.mensajeError = err
                        });
                } else {
                    console.log("en actualizar");

                    this.solicitudService.updateSolicitud(p)
                        .subscribe({
                            next: () => {
                                this.onSaveComplete();
                            },
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
    
    displayProyectos() {
        this.solicitudForm.get('proyecto').enable();
    }

    displayProyecto(proyectoSelected: Proyecto) {
        this.solicitudForm.get('_codigo_proyecto').setValue(proyectoSelected.codigoProyecto);
        this.solicitudForm.get('_centro_costos_proyecto').setValue(proyectoSelected.centroCostos);        
    }

    displayValorSelected() {
        this.solicitudForm.get('_valor_selected').setValue(this.solicitudForm.get('precotizacionDto').value.valorTotal)
    }

    displayInvestigador(investigadorSelected: Investigador) {
        this.solicitudForm.get('_identificacion_investigador').setValue(investigadorSelected.identificacion);
    }

    displayLineasEspecificas(detalleSeleted : FormGroup) {
        if(detalleSeleted.controls['lineaGeneral'].value){
            detalleSeleted.controls['lineaEspecifica'].enable();            
        }
    }

    displayProveedor(precotizacionSelected: FormGroup){
        if(precotizacionSelected.controls['proveedor'].value){
            precotizacionSelected.controls['_identificacion_proveedor'].setValue(precotizacionSelected.controls['proveedor'].value.identificacion);
            precotizacionSelected.controls['_telefono_proveedor'].setValue(precotizacionSelected.controls['proveedor'].value.telefono);
            precotizacionSelected.controls['_ciudad_proveedor'].setValue(precotizacionSelected.controls['proveedor'].value.ciudad);
        }
    }


    getParametroNecesidad(){
        return this.parametroNecesidadService.getParametroNecesidadActivo().subscribe({
            next: parametro => {
                this.parametroNecesidad = parametro;
                this.solicitudForm.get('necesidad').setValue(this.parametroNecesidad.descripcion);
            },
            error: error => this.mensajeError = error
        });
    }

    getParametroObservacion(){
        return this.parametroObservacionService.getParametroObservacionActivo().subscribe({
            next: parametro => {
                this.parametroObservacion = parametro;
                this.solicitudForm.get('observacion').setValue(this.parametroObservacion.descripcion);
            },
            error: error => this.mensajeError = error
        });
    }

    getParametroArgumento(){
        return this.parametroArgumentoService.getParametroArgumentos().subscribe({
            next: parametros => this.argumentos = parametros,
            error: error => this.mensajeError = error
        });
    }

    getProveedores() {
        return this.proveedorService.getProveedores().subscribe({
            next: proveedores => {
                this.proveedores = proveedores;                
            },
            error: error => this.mensajeError = error
        });
    }

    getGrupos() {
        return this.grupoService.getGrupos().subscribe({
            next: grupos => this.grupos = grupos,
            error: error => this.mensajeError = error
        })
    }

    getLineaGeneral() {
        return this.lineaGeneralService.getLineaGenerales().subscribe({
            next: lineasGenerales => this.lineasGenerales = lineasGenerales,
            error: error => this.mensajeError = error
        })
    }

    getInvestigadores() {
        return this.investigadorService.getInvestigadores().subscribe({
            next: investigadores => this.investigadores = investigadores,
            error: error => this.mensajeError = error
        })
    }
}

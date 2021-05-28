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
    
    tipoTramites = ['Nacional', 'Internacional'];

    get detalleTramiteDtos() {
        return this.solicitudForm.get('detalleTramiteDtos') as FormArray;
    }
    get precotizacionDtos(): FormArray {
        return this.solicitudForm.get('precotizacionDtos') as FormArray;
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
        private lineaGeneralService: LineaGeneralService) { }

    ngOnInit() {        
        this.getGrupos();
        this.getProveedores();
        this.getInvestigadores();
        this.getParametroNecesidad();
        this.getLineaGeneral();

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
            nombreContacto: '',
            telefonoContacto: '',
            cargo: '',
            necesidad: '',
            proyecto: '',
            fecha: [new Date(), [Validators.required]],            
            detalleTramiteDtos: this.fb.array([this.buildDetails()]),
            valor: '',
            verificacion: '',
            precotizacionDtos: this.fb.array([this.buildPrecotizacion()]),

            descripcion: ['', [Validators.required]],
            observacion: ['', [Validators.required]]
        });


        if (this.agregarSolicitud) {
            // this.addPrecotizacion();
            // this.solicitudForm.patchValue({fecha:  (this.getDate())})
        }


    }

    ngOnDestroy(): void {
        this.sub.unsubscribe();
    }

    buildDetails() {
        return this.fb.group({
            lineaGeneral: '',
            lineaEspecifica: '',
            descripcion: '',
            cantidad: ''
        });
    }

    addDetails(){
        this.detalleTramiteDtos.push(this.buildDetails());                
    }

    deleteDetails(index: number) {
        this.detalleTramiteDtos.removeAt(index);
    }


    update(id) {
        this.grupoService.getGrupo(id).subscribe({
            next: grupo => {
                this.grupo = grupo;
                this.solicitudForm.controls['investigador'].get('idInvestigador').enable();
            },
            error: err => this.mensajeError = err
        });
        // console.log(this.solicitudForm.controls['investigador'].get('identificacion').value);
    }

    updateInvestigador(id) {
        this.investigadorService.getInvestigador(id).subscribe({
            next: investigador => {
                this.investigador = investigador;
                this.solicitudForm.controls['investigador'].get('identificacion').setValue(this.investigador.identificacion);
                this.solicitudForm.controls['investigador'].get('telefono').setValue(this.investigador.telefono);
            },
            error: err => this.mensajeError = err
        });
    }

    updateProveedor(id, address) {
        console.log(address);



        this.proveedorService.getProveedor(id).subscribe({
            next: proveedor => {
                this.proveedor = proveedor;
                address.get('proveedor').controls['telefono'].setValue(this.proveedor.telefono);
                address.get('proveedor').controls['nit'].setValue(this.proveedor.identificacion);
            },
            error: err => this.mensajeError = err
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

    deletePrecotizacion(index: number) {
        this.precotizacionDtos.removeAt(index);
        this.precotizacionDtos.markAsDirty();
    }

    onSaveComplete(): void {
        this.solicitudForm.reset();
        this.router.navigate(['/solicitud']);
    }

    displaySolicitud(solicitud: Solicitud): void {
        if (this.solicitudForm) {
            this.solicitudForm.reset();
        }
        this.solicitud = solicitud;

        if (this.solicitud.id === 0) {
            this.title = "Agregar solicitud";
        } else {
            this.title = `Editar solicitud: ${this.solicitud.id}`;
            this.solicitudForm.controls['investigador'].get('idInvestigador').enable();
            this.update(this.solicitud.grupoInvestigador.investigador.id)
            this.updateInvestigador(this.solicitud.grupoInvestigador.investigador.id)
        }
        this.solicitudForm.patchValue({
            grupo: { idGrupo: this.solicitud.grupoInvestigador.grupo.id },
            investigador: { idInvestigador: this.solicitud.grupoInvestigador.investigador.id },
            cargo: this.solicitud.grupoInvestigador.cargo,
            nombreProyecto: this.solicitud.grupoInvestigador.nombreContacto,
            // rubro: this.solicitud.rubro,
            // subrubro: this.solicitud.subrubro,
            // financiador: this.solicitud.financiador,
            // centroCostos: this.solicitud.centroCostos,
            fecha: this.solicitud.fecha,
            necesidad: this.solicitud.necesidad,
            descripcion: this.solicitud.observacion,
            valor: this.solicitud.valor,
            verificacion: this.solicitud.verificacion,
            observacion: this.solicitud.observacion

        });
        let control = <FormArray>this.solicitudForm.controls['precotizaciones']
        this.proveedorExisting = true;
        console.log(this.solicitudForm.controls['precotizaciones']);
        // for (let i = 0; i < this.solicitud.precotizaciones.length; i++) {
        //     // control.push(this.fb.group(this.solicitud.precotizaciones[i]));
        //     control.push(this.fb.group({
        //         proveedor: this.fb.group({
        //             idProveedor: this.solicitud.precotizaciones[i].proveedor.id,
        //             telefono: this.solicitud.precotizaciones[i].proveedor.telefono,
        //             nit: this.solicitud.precotizaciones[i].proveedor.identificacion
        //         }),
        //         valor: this.solicitud.precotizaciones[i].valorTotal
        //     }));
        // }
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

    getProyectos(id) {                
        this.grupoSelected = this.grupos.find(t => t.id == id);                                
        this.proyectos = this.grupoSelected.proyectos;        
    }

    getProyecto(id) {
        this.proyectoSelected = this.grupoSelected.proyectos.find(t => t.id == id);        
    }

    geIdentificacion(id) {
        this.investigadorSelected = this.investigadores.find(t => t.id == id);
        console.log(this.investigadorSelected);
        
    }

    getLineasEspecificas(id : number): lineaEspecifica[] {
        if(id){         
            return this.lineasGenerales.find(t => t.id == id).lineaEspecificas;
        }
    }

    getLinea(str: number) {
        return this.lineasEspecificas.filter(t => t.id === str)
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

    getProveedores() {
        return this.proveedorService.getProveedores().subscribe({
            next: proveedores => this.proveedores = proveedores,
            error: error => this.mensajeError = error
        });
    }



    addPrecotizacion(): void {
        this.precotizacionDtos.push(this.buildPrecotizacion());

    }

    buildPrecotizacion(): FormGroup {
        return this.fb.group({
            proveedor: this.fb.group({
                id: '',
                nombre: '',
                nit: '',
                telefono: '',
                ciudad: '',
                tipo: '',
                // telefono: {value: '', disabled:true} ,
                // nit:{value: '', disabled:true}
            }),
            valorTotal: '',
            valorIva: ''            
        })
    }

}

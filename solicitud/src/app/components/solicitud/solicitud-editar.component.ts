import { Component, OnInit, OnDestroy } from '@angular/core';
import { Solicitud } from '../../models/solicitud';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { Subscription, zip } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { SolicitudService } from '../../services/solicitud.service';
import { GrupoService } from '../../services/grupo.service';
import { Grupo } from '../../models/grupo';
import { Proveedor } from '../../models/proveedor';
import { ProveedorService } from '../../services/proveedor.service';
import { InvestigadorService } from '../../services/investigador.service';
import { Investigador } from '../../models/investigador';

@Component({
    templateUrl: 'solicitud-editar.component.html'
})

export class SolicitudEditarComponent implements OnInit, OnDestroy {
    title = '';
    mensajeError: string;
    agregarSolicitud: boolean;
    proveedorExisting = false;
    bsValue= new Date();

    solicitudForm: FormGroup;
    solicitud: Solicitud;
    grupo: Grupo;
    grupos: Grupo[];
    investigador: Investigador;
    investigadores: Investigador[];
    proveedores: Proveedor[];
    proveedor: Proveedor;
    idGrupo: FormControl;

    private sub: Subscription;


    get precotizaciones(): FormArray {
        return this.solicitudForm.get('precotizaciones') as FormArray;
    }

    constructor(private solicitudService: SolicitudService,
        private router: Router,
        private route: ActivatedRoute,
        private fb: FormBuilder,
        private grupoService: GrupoService,
        private proveedorService: ProveedorService,
        private investigadorService: InvestigadorService) { }

    ngOnInit() {
        this.getGrupos();
        this.getProveedores();
        this.getInvestigadores();

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
            grupo: this.fb.group({
                idGrupo: ''
            }),
            investigador: this.fb.group({
                idInvestigador: { value: '', disabled: true },
                identificacion: { value: '', disabled: true },
                telefono: { value: '', disabled: true }
            }),

            cargo: ['', [Validators.required]],
            nombreProyecto: ['Estos es una prueba', [Validators.required]],
            rubro: '',
            subrubro: '',
            financiador: '',
            centroCostos: '',

            fecha: [new Date(), [Validators.required]],
            necesidad: ['', [Validators.required]],
            descripcion: ['', [Validators.required]],
            valor: ['', [Validators.required]],
            verificacion: '',
            observacion: ['', [Validators.required]],
            precotizaciones: this.fb.array([])
        });


        if (this.agregarSolicitud) {
            this.addPrecotizacion();
            // this.solicitudForm.patchValue({fecha:  (this.getDate())})
        }


    }

    ngOnDestroy(): void {
        this.sub.unsubscribe();
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
                address.get('proveedor').controls['nit'].setValue(this.proveedor.nit);
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
        if (this.solicitud.idSolicitud === 0) {
            this.onSaveComplete();
        } else {
            if (confirm(`Realmente desea eliminar la solicitud: ${this.solicitud.nombreProyecto}?`)) {
                this.solicitudService.deleteSolicitud(this.solicitud.idSolicitud)
                    .subscribe({
                        next: () => this.onSaveComplete(),
                        error: err => this.mensajeError = err
                    });
            }
        }
    }

    deletePrecotizacion(index: number) {
        this.precotizaciones.removeAt(index);
        this.precotizaciones.markAsDirty();
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

        if (this.solicitud.idSolicitud === 0) {
            this.title = "Agregar solicitud";
        } else {
            this.title = `Editar solicitud: ${this.solicitud.idSolicitud}`;
            this.solicitudForm.controls['investigador'].get('idInvestigador').enable();
            this.update(this.solicitud.grupo.idGrupo)
            this.updateInvestigador(this.solicitud.investigador.idInvestigador)
        }
        this.solicitudForm.patchValue({
            grupo: { idGrupo: this.solicitud.grupo.idGrupo },
            investigador: { idInvestigador: this.solicitud.investigador.idInvestigador },
            cargo: this.solicitud.cargo,
            nombreProyecto: this.solicitud.nombreProyecto,
            rubro: this.solicitud.rubro,
            subrubro: this.solicitud.subrubro,
            financiador: this.solicitud.financiador,
            centroCostos: this.solicitud.centroCostos,
            fecha: this.solicitud.fecha,
            necesidad: this.solicitud.necesidad,
            descripcion: this.solicitud.descripcion,
            valor: this.solicitud.valor,
            verificacion: this.solicitud.verificacion,
            observacion: this.solicitud.observacion

        });
        let control = <FormArray>this.solicitudForm.controls['precotizaciones']
        this.proveedorExisting = true;
        console.log(this.solicitudForm.controls['precotizaciones']);
        for (let i = 0; i < this.solicitud.precotizaciones.length; i++) {
            // control.push(this.fb.group(this.solicitud.precotizaciones[i]));
            control.push(this.fb.group({
                proveedor: this.fb.group({
                    idProveedor: this.solicitud.precotizaciones[i].proveedor.idProveedor,
                    telefono: this.solicitud.precotizaciones[i].proveedor.telefono,
                    nit: this.solicitud.precotizaciones[i].proveedor.nit
                }),
                valor: this.solicitud.precotizaciones[i].valor
            }));
        }
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
            error: err => this.mensajeError = err
        })
    }

    getInvestigadores() {
        return this.investigadorService.getInvestigadores().subscribe({
            next: investigadores => this.investigadores = investigadores,
            error: err => this.mensajeError = err
        })
    }

    getProveedores() {
        return this.proveedorService.getProveedores().subscribe({
            next: proveedores => this.proveedores = proveedores,
            error: err => this.mensajeError = err
        })
    }



    addPrecotizacion(): void {
        this.precotizaciones.push(this.buildPrecotizacion());

    }

    buildPrecotizacion(): FormGroup {
        return this.fb.group({
            proveedor: this.fb.group({
                idProveedor: '',
                telefono: '',
                nit: ''
                // telefono: {value: '', disabled:true} ,
                // nit:{value: '', disabled:true}
            }),
            valor: ''
        })
    }

    updateUSAmount(event) {
        // this.solicitudForm.get('valor').setValue(event.target.value);
    }

    updateUSAmount2(event) {
        // this.solicitudForm.controls['precotizaciones'].get('valor').setValue(event.target.value);
    }

}
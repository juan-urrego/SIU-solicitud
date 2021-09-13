import { Component, OnInit, OnDestroy } from '@angular/core';
import { Solicitud, SolicitudResolved } from '../solicitud';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SolicitudService } from '../solicitud.service';
import { ConfirmationService, MenuItem, MessageService } from 'primeng/api';
import { AuthService } from 'src/app/auth/auth.service';


@Component({
    templateUrl: 'solicitud-editar.component.html',
    styleUrls: ['solicitud-editar.component.scss']
})

export class SolicitudEditarComponent implements OnInit {
    title = '';
    mensajeError: string;
    solicitudForm: FormGroup;
    solicitud: Solicitud;
    delayToast = 3000;


    items: MenuItem[];

    get isDirty(): boolean {
        return this.solicitudForm.dirty;
    }

    constructor(private solicitudService: SolicitudService,
        private router: Router,
        private route: ActivatedRoute,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private authService: AuthService) { }

    ngOnInit() {        
        this.items = [{
            label: 'Informacion',
            routerLink: 'info'
        },
        {
            label: 'Proyecto',
            routerLink: 'proyecto'
        },
        {
            label: 'Detalles',
            routerLink: 'detalle'
        },
        {
            label: 'Precotizaciones',
            routerLink: 'precotizacion'
        }
    ];

        this.route.data.subscribe(data => {
            const resolvedData: SolicitudResolved = data['resolvedData'];
            this.mensajeError = resolvedData.error;
            this.solicitud = resolvedData.solicitud;
            this.solicitudForm = resolvedData.form
        });
        if(this.authService.getRole() === "director"){ 
            this.solicitudForm.disable();
        }        
    }

    onSaveComplete(): void {
        this.solicitudForm.reset();
        this.router.navigate(['/solicitud']);
    }    


    deleteSolicitud(): void {
        if(this.solicitud){
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar esta solicitud?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.solicitudService.deleteSolicitud(this.solicitud.id).subscribe({
                        next: () => {
                            this.onSaveComplete();
                        },
                        error: error => {
                            this.mensajeError = error
                            this.messageService.add({
                                severity:'error',
                                summary: 'Error',
                                detail: this.mensajeError,
                                life: this.delayToast
                            });
                        }
                    });
                }
            });
        }
    }

    
    saveSolicitud(): void {
        if (this.solicitudForm.valid) {
            if (this.solicitudForm.dirty) {
                this.solicitudForm.get('usuarioEmail').setValue(this.authService.getEmail());
                const p = { ...this.solicitud, ...this.solicitudForm.value };
                delete p.grupo.proyectos;
                console.log(p);
                if (!this.solicitud) {
                    this.solicitudService.createSolicitud(p)
                        .subscribe({
                            next: () => {
                                this.onSaveComplete();
                            },
                            error: error => {
                                this.mensajeError= error.message;
                                this.messageService.add({
                                    severity:'error',
                                    summary: 'Error',
                                    detail: this.mensajeError,
                                    life: this.delayToast
                                });
                            }
                        });
                } else {
                    this.solicitudService.updateSolicitud(p)
                        .subscribe({
                            next: () => {
                                this.onSaveComplete();
                            },
                            error: error => {
                                this.mensajeError = error;
                                this.messageService.add({
                                    severity:'error',
                                    summary: 'Error',
                                    detail: this.mensajeError,
                                    life: this.delayToast
                                });
                            }
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

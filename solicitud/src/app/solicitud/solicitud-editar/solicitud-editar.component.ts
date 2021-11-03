import { Component, OnInit, OnDestroy } from '@angular/core';
import { Mail, Solicitud, SolicitudResolved } from '../solicitud';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
    emailForm: FormGroup;
    emailDialog: boolean;
    emailLogged: string;
    mail: Mail;


    items: MenuItem[];

    get isDirty(): boolean {
        return this.solicitudForm.dirty;
    }

    constructor(private solicitudService: SolicitudService,
        private router: Router,
        private route: ActivatedRoute,
        private messageService: MessageService,
        private confirmationService: ConfirmationService,
        private authService: AuthService,
        private fb: FormBuilder) {
            this.emailLogged = this.authService.getEmail();
         }

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

    openNew(): void {
        this.emailForm = this.fb.group({
            nombre: ['', Validators.required],
            originEmail: this.emailLogged,
            destinyEmail: this.solicitud.grupoInvestigador.investigador.email,
            asunto: `Observaciones de solicitud de trámite #${this.solicitud.id}`,
            cuerpo: ['', Validators.required]
        });
        this.emailDialog = true;
    }

    hideDialog() {
        this.emailForm.reset();
        this.emailDialog = false;        
    }

    enviarEmail(){
        console.log("correo enviado");
        if(this.emailForm.valid){
            console.log("esto es uns save");
            if(this.emailForm.dirty){
                const p = {...this.mail, ...this.emailForm.value}
                this.solicitudService.enviarCorreo(this.solicitud.id, p).subscribe({
                    next: () => {
                        this.onSaveComplete();
                    },
                    error: error => {
                        this.mensajeError = error.message;
                        this.messageService.add({
                            severity:'error',
                            summary: 'Error',
                            detail: this.mensajeError,
                            life: this.delayToast
                        });
                    }
                });
                
            }
        }
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

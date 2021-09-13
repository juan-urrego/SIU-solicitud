import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Usuario, UsuarioResolved } from './usuario';
import { ConfirmationService, MessageService } from 'primeng/api';
import { UserService } from './usuario.service';

@Component({
    templateUrl: './usuario-editar.component.html'
})
export class UsuarioEditarComponent implements OnInit {

    title: string;
    mensajeError: string;
    usuarioForm: FormGroup;
    usuario: Usuario;
    delayToast = 3000;
    loadImage: boolean;
    submitted: boolean;
    file: any = null;
    url: any;
    rolOptions: string[] = ['usuario','admin','director']


    get roles(): FormArray {
        return this.usuarioForm.get('roles') as FormArray;
      }


    constructor(private fb: FormBuilder,
        private usuarioService: UserService,
        private router: Router,
        private route: ActivatedRoute,
        private messageService: MessageService,
        private confirmationService: ConfirmationService) { }

    ngOnInit() {
        this.route.data.subscribe(data => {
            const resolvedData: UsuarioResolved = data['resolvedData'];
            this.mensajeError = resolvedData.error;
            this.usuario = resolvedData.usuario;
            this.usuarioForm = resolvedData.form;
        });
        this.loadFirma();
    }

    addTag(): void {
        this.roles.push(new FormControl());
      }
    
    deleteTag(index: number): void {
    this.roles.removeAt(index);
    this.roles.markAsDirty();
    }

    loadFirma() {
        if (this.usuario) {
            this.usuarioService.getUsuarioFirma(this.usuario.id).subscribe({
                next: image => this.createImageFromBlob(image),
                error: error => this.mensajeError = error
            });
        }
    }

    createImageFromBlob(image: Blob) {
        let reader = new FileReader();
        reader.addEventListener("load", () => {
            this.url = reader.result;
        }, false);
        if (image) {
            reader.readAsDataURL(image);
        }
    }

    toggleImage(): void {
        this.loadImage = !this.loadImage;
    }

    onFileSelected(event) {
        this.file = event.currentFiles[0]
    }


    onSaveComplete(): void {
        this.usuarioForm.reset();
        this.router.navigate(['/usuario']);
    }

    deleteUser(): void {
        if (this.usuario) {
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar este usuario?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.usuarioService.deleteUser(this.usuario.id).subscribe({
                        next: () => {
                            this.onSaveComplete();
                        },
                        error: error => {
                            this.mensajeError = error
                            this.messageService.add({
                                severity: 'error',
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

    saveUser(): void {
        if (this.usuarioForm.valid) {
            if (this.usuarioForm.dirty) {
                if (!this.usuario) {
                    this.usuarioService.createUser(JSON.stringify(this.usuarioForm.value), this.file)
                        .subscribe({
                            next: () => {
                                this.onSaveComplete();
                            },
                            error: error => {
                                this.mensajeError = error.message;
                                this.messageService.add({
                                    severity: 'error',
                                    summary: 'Error',
                                    detail: this.mensajeError,
                                    life: this.delayToast
                                });
                            }
                        });
                } else {
                    this.usuarioService.updateUser(JSON.stringify(this.usuarioForm.value), this.file, this.usuario.id)
                        .subscribe({
                            next: () => {
                                this.onSaveComplete();
                            },
                            error: error => {
                                this.mensajeError = error;
                                this.messageService.add({
                                    severity: 'error',
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

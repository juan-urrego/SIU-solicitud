import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { User, UserResolved } from './user';
import { ConfirmationService, MessageService } from 'primeng/api';
import { UserService } from './user.service';

@Component({
    templateUrl: './user-edit.component.html'
})
export class UserEditComponent implements OnInit {

    title: string;
    mensajeError: string;
    usuarioForm: FormGroup;
    user: User;
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
            const resolvedData: UserResolved = data['resolvedData'];
            this.mensajeError = resolvedData.error;
            this.user = resolvedData.user;
            this.usuarioForm = resolvedData.form;
        });
        this.loadFirma();
        console.log(this.url);
        
    }

    loadFirma() {
        if (this.user) {
            this.usuarioService.getFirmaById(this.user.id).subscribe({
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
        console.log(this.file);
        
    }

    onSaveComplete(): void {
        this.usuarioForm.reset();
        this.router.navigate(['/usuario']);
    }

    deleteUser(): void {
        if (this.user) {
            this.confirmationService.confirm({
                message: '¿Estás seguro de eliminar este usuario?',
                header: 'Confirmacion',
                icon: 'pi pi-exclamation-triangle',
                accept: () => {
                    this.usuarioService.deleteUser(this.user.id).subscribe({
                        next: () => {
                            this.onSaveComplete();
                        },
                        error: error => {
                            this.mensajeError = error.error.message
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
                const p = { ...this.user, ...this.usuarioForm.value}
                if (!this.user) {
                    this.usuarioService.createUser(p, this.file)
                        .subscribe({
                            next: () => {
                                this.onSaveComplete();
                            },
                            error: error => {
                                this.mensajeError = error.error.message;
                                this.messageService.add({
                                    severity: 'error',
                                    summary: 'Error',
                                    detail: this.mensajeError,
                                    life: this.delayToast
                                });
                            }
                        });
                } else {
                    this.usuarioService.updateUser(p, this.file, this.user.id)
                        .subscribe({
                            next: () => {
                                this.onSaveComplete();
                            },
                            error: error => {
                                this.mensajeError = error.error.message;
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

import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { AuthService } from '../auth/auth.service';
import { User } from './user';
import { UserService } from './user.service';


@Component({
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {
  titulo: string = 'Lista de usuarios'
  mensajeError: string;
  users: User[];
  email: string;

  constructor(private usuarioService: UserService,
              private authService: AuthService,
              private messageService: MessageService,
              private confirmationService: ConfirmationService) {
                this.email = authService.getEmail();
  }

  ngOnInit(): void {
      this.refresh();
  }

  refresh() {
    this.usuarioService.getUsers().subscribe({
        next: users => {
            this.users = users;
        },
        error: err => this.mensajeError = err
    });
  }

  activateUser(id: number, isActive: boolean) {
    this.confirmationService.confirm({
      message: '¿Estás seguro de activar/desactivar este usuario?',
      header: 'Confirmacion',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.usuarioService.activeUser(id, isActive).subscribe({
              next: (mensaje: any) => {
                  this.refresh();
                  console.log(mensaje.message);
                  
                  this.messageService.add({
                      severity:'success',
                      summary: 'Actualizado',
                      detail: mensaje.message
                  });
              },
              error: error => {
                  this.mensajeError = error.error.message;
                  this.messageService.add({
                      severity:'error',
                      summary: 'Error',
                      detail: this.mensajeError
                  });
              }
          });
      }
  });
  }
}

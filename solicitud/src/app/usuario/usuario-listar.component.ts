import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UserService } from './usuario.service';


@Component({
  templateUrl: './usuario-listar.component.html'
})
export class UsuarioListarComponent implements OnInit {
  titulo: string = 'Lista de usuarios'
  mensajeError: string;
  users: Usuario[]

  constructor(private usuarioService: UserService) { }

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
}

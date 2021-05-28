import { Component, OnInit } from '@angular/core';
import { NuevoUsuario } from 'src/app/shared/models/login/nuevo-usuario';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  templateUrl: './usuario-listar.component.html'
})
export class UsuarioListarComponent implements OnInit {
  titulo: string = 'Lista de Auxiliares'
  mensajeError: string;

  _filtrar = '';
  get filtrar(): string {
      return this._filtrar;
  }
  set filtrar(value: string) {
      this._filtrar = value;
      this.filtrados = this.filtrar ? this.performFilter(this.filtrar) : this.auxiliares;
  }

  auxiliares: NuevoUsuario[]
  filtrados: NuevoUsuario[]

  constructor(private auxiliarService: AuthService) { }

  ngOnInit(): void {
    //   this.auxiliarService.getAuxiliares().subscribe({
    //       next: auxiliares => {
    //           this.auxiliares = auxiliares;;
    //           this.filtrados = this.auxiliares;
              
    //       },
    //       error: err => this.mensajeError = err
    //   });

  }

  performFilter(filterBy: string): NuevoUsuario[] {
      filterBy = filterBy.toLocaleLowerCase();
      return this.auxiliares.filter((auxiliares: NuevoUsuario) =>
          auxiliares.nombre.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }
}

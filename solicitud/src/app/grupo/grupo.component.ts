import { Component, OnInit } from '@angular/core';
import { Grupo } from '../models/grupo';
import { GrupoService } from '../services/grupo.service';

@Component({
    templateUrl: 'grupo.component.html'
})

export class GrupoComponent implements OnInit {
    titulo: string = 'Lista de grupos'
    mensajeError: string;

    _filtrar = '';
    get filtrar(): string {
        return this._filtrar;
    }
    set filtrar(value: string) {
        this._filtrar = value;
        this.filtrados = this.filtrar ? this.performFilter(this.filtrar) : this.grupos;
    }

    grupos: Grupo[]
    filtrados: Grupo[]

    constructor(private grupoService: GrupoService) { }

    ngOnInit(): void {
        this.grupoService.getGrupos().subscribe({
            next: grupos => {
                this.grupos = grupos;;
                this.filtrados = this.grupos;
            },
            error: err => this.mensajeError = err
        });

    }

    performFilter(filterBy: string): Grupo[] {
        filterBy = filterBy.toLocaleLowerCase();
        return this.grupos.filter((grupo: Grupo) =>
            grupo.nombre.toLocaleLowerCase().indexOf(filterBy) !== -1);
    }
}
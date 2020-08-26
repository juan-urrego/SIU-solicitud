import { Component, OnInit } from '@angular/core';
import { Investigador } from '../../models/investigador';
import { InvestigadorService } from '../../services/investigador.service';

@Component({
    templateUrl: 'investigador.component.html'
})

export class InvestigadorComponent implements OnInit {
    titulo: string = 'Lista de Investigadores'
    mensajeError: string;

    _filtrar = '';
    get filtrar(): string {
        return this._filtrar;
    }
    set filtrar(value: string) {
        this._filtrar = value;
        this.filtrados = this.filtrar ? this.performFilter(this.filtrar) : this.investigadores;
    }

    investigadores: Investigador[]
    filtrados: Investigador[]

    constructor(private investigadorService: InvestigadorService) { }

    ngOnInit(): void {
        this.investigadorService.getInvestigadores().subscribe({
            next: investigadores => {
                this.investigadores = investigadores;;
                this.filtrados = this.investigadores;
            },
            error: err => this.mensajeError = err
        });

    }

    performFilter(filterBy: string): Investigador[] {
        filterBy = filterBy.toLocaleLowerCase();
        return this.investigadores.filter((investigador: Investigador) =>
            investigador.nombre.toLocaleLowerCase().indexOf(filterBy) !== -1);
    }
}
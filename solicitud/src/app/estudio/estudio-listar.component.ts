import { Component, OnInit } from '@angular/core';
import { Estudio } from './estudio';
import { EstudioService } from './estudio.service';

@Component({    
    templateUrl: 'estudio-listar.component.html'
})

export class EstudioListarComponent implements OnInit {
    titulo: string = 'Lista de Estudios'
    mensajeError: string;

    _filtrar = '';
    get filtrar(): string {
        return this._filtrar;
    }
    set filtrar(value: string) {
        this._filtrar = value;
        this.filtrados = this.filtrar ? this.performFilter(this.filtrar) : this.estudios;
    }

    estudios: Estudio[]
    filtrados: Estudio[]

    constructor(private estudioService: EstudioService) { }

    ngOnInit(): void {
        this.estudioService.getEstudios().subscribe({
            next: estudios => {
                this.estudios = estudios;;
                this.filtrados = this.estudios;
            },
            error: err => this.mensajeError = err
        });

    }

    performFilter(filterBy: string): Estudio[] {
        filterBy = filterBy.toLocaleLowerCase();
        return this.estudios.filter((estudio: Estudio) =>
            estudio.estado.nombre.toLocaleLowerCase().indexOf(filterBy) !== -1);
    }
}
import { Component, OnInit } from '@angular/core';
import { Consulta } from '../../models/consulta';
import { ConsultaService } from '../../services/consulta.service';

@Component({
    
    templateUrl: 'consulta.component.html'
})

export class ConsultaComponent implements OnInit {
    titulo: string = 'Lista de Consultas'
    mensajeError: string;

    _filtrar = '';
    get filtrar(): string {
        return this._filtrar;
    }
    set filtrar(value: string) {
        this._filtrar = value;
        this.filtrados = this.filtrar ? this.performFilter(this.filtrar) : this.consultas;
    }

    consultas: Consulta[]
    filtrados: Consulta[]

    constructor(private consultaService: ConsultaService) { }

    ngOnInit(): void {
        this.consultaService.getConsultas().subscribe({
            next: consultas => {
                this.consultas = consultas;;
                this.filtrados = this.consultas;
            },
            error: err => this.mensajeError = err
        });

    }

    performFilter(filterBy: string): Consulta[] {
        filterBy = filterBy.toLocaleLowerCase();
        return this.consultas.filter((consulta: Consulta) =>
            consulta.estado.nombre.toLocaleLowerCase().indexOf(filterBy) !== -1);
    }
}
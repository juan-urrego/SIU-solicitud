import { Component, OnInit } from '@angular/core';
import { Estudio } from './estudio';
import { EstudioService } from './estudio.service';

@Component({    
    templateUrl: 'estudio-listar.component.html'
})

export class EstudioListarComponent implements OnInit {
    titulo: string = 'Lista de estudios'
    mensajeError: string;
    estudios: Estudio[]


    constructor(private estudioService: EstudioService) { }

    ngOnInit(): void {
        this.refresh();
    }

    refresh() {
        this.estudioService.getEstudios().subscribe({
            next: estudios => {
                this.estudios = estudios;;
            },
            error: err => this.mensajeError = err
        });
    }
}
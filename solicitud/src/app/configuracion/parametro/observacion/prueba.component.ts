import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';


@Component({
    selector: 'app-details',
    templateUrl: './prueba.component.html'
})

export class PruebaComponent implements OnInit, OnDestroy {
    @Input() display: boolean;
    @Output() displayChange = new EventEmitter()
    constructor() { }

    ngOnDestroy(): void {
        this.displayChange.unsubscribe();
    }

    ngOnInit() { }

    onClose(){
        this.displayChange.emit(false);
    }

}
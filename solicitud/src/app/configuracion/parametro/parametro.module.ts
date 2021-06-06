import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';

import { ParametroAcademicaComponent } from './unidad-academica/parametro-academica.component';
import { ParametroAcuerdoComponent } from './acuerdo/parametro-acuerdo.component';
import { ParametroArgumentoComponent } from './argumento/parametro-argumento.component';
import { ParametroConsultaComponent } from './consulta/parametro-consulta.component';
import { ParametroNecesidadComponent } from './necesidad/parametro-necesidad.component';
import { ParametroObservacionComponent } from './observacion/parametro-observacion.component';
import { ParametroRoutingModule } from './parametro-routing.module';
import { ParametroComponent } from './parametro.component';
import { PruebaComponent } from './observacion/prueba.component';



@NgModule({
    imports: [
        SharedModule,
        ParametroRoutingModule
    ],
    exports: [],
    declarations: [
        ParametroAcademicaComponent,
        ParametroAcuerdoComponent,
        ParametroArgumentoComponent,
        ParametroConsultaComponent,
        ParametroNecesidadComponent,
        ParametroObservacionComponent,
        PruebaComponent,
        ParametroComponent
    ],
    providers: [],
})
export class ParametroModule { }

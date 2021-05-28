import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { ParametroAcademicaComponent } from "./unidad-academica/parametro-academica.component";
import { ParametroAcuerdoComponent } from "./acuerdo/parametro-acuerdo.component";
import { ParametroArgumentoComponent } from "./argumento/parametro-argumento.component";
import { ParametroConsultaComponent } from "./consulta/parametro-consulta.component";
import { ParametroNecesidadComponent } from "./necesidad/parametro-necesidad.component";
import { ParametroObservacionComponent } from "./observacion/parametro-observacion.component";
import { ParametroComponent } from "./parametro.component";

@NgModule({
    imports: [
        RouterModule.forChild([
            { path: '', component: ParametroComponent}
        ])
    ],
    exports: [RouterModule]
})
export class ParametroRoutingModule{}
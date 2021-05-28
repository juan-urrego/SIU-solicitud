import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { SolGuardService as guard } from '../auth/auth.guard';
import { ConsultaEditarComponent } from "./consulta-editar.component";
import { ConsultaListarComponent } from "./consulta-listar.component";

@NgModule({
    imports: [

        RouterModule.forChild([
            {
                path: '' , 
                component : ConsultaListarComponent
            },
            {
                path: ':id/editar', 
                component: ConsultaEditarComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class ConsultaRoutingModule{}
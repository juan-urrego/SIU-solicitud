import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { SolGuardService as guard } from '../auth/auth.guard';
import { ConsultaEditarComponent } from "./consulta-editar.component";
import { ConsultaListarComponent } from "./consulta-listar.component";
import { ConsultaResolver } from "./consulta-resolver.service";

@NgModule({
    imports: [

        RouterModule.forChild([
            {
                path: '' , 
                component : ConsultaListarComponent
            },
            {
                path: ':id/editar', 
                resolve: { resolvedData: ConsultaResolver},
                component: ConsultaEditarComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class ConsultaRoutingModule{}
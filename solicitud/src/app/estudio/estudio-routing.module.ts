import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { SolGuardService as guard } from '../auth/auth.guard';
import { EstudioEditarComponent } from "./estudio-editar.component";
import { EstudioListarComponent } from "./estudio-listar.component";

@NgModule({
    imports: [

        RouterModule.forChild([
            {
                path: '' , 
                component : EstudioListarComponent
            },
            {
                path: ':id/editar', 
                component: EstudioEditarComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class EstudioRoutingModule{}
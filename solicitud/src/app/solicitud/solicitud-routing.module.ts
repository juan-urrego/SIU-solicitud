import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { SolGuardService as guard } from '../auth/auth.guard';
import { SolicitudEditarComponent } from "./solicitud-editar.component";
import { SolicitudListarComponent } from "./solicitud-listar.component";

@NgModule({
    imports: [

        RouterModule.forChild([
            {
                path: '',
                component: SolicitudListarComponent
            },
            {
                path: ':id/editar',
                component: SolicitudEditarComponent
            }


        ])
    ],
    exports: [RouterModule]
})
export class SolicitudRoutingModule { }
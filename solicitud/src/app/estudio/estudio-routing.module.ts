import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { EstudioEditarComponent } from "./estudio-editar.component";
import { EstudioListarComponent } from "./estudio-listar.component";
import { EstudioResolver } from "./estudio-resolver.service";

@NgModule({
    imports: [

        RouterModule.forChild([
            {
                path: '' , 
                component : EstudioListarComponent
            },
            {
                path: ':id/editar',
                resolve: { resolvedData: EstudioResolver}, 
                component: EstudioEditarComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class EstudioRoutingModule{}
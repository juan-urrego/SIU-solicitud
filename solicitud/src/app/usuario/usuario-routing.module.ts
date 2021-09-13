import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { SolGuardService as guard } from '../auth/auth.guard';
import { UsuarioEditarComponent } from "./usuario-editar.component";
import { UsuarioListarComponent } from "./usuario-listar.component";
import { UsuarioResolver } from "./usuario-resolver.component";

@NgModule({
    imports: [

        RouterModule.forChild([
            {
                path: '' , 
                canActivate: [guard],
                data: { expectedRol : ['admin']},
                component : UsuarioListarComponent
            },
            {
                path: ':id/editar', 
                canActivate: [guard],
                resolve: { resolvedData: UsuarioResolver},
                data: { expectedRol : ['admin']},
                component: UsuarioEditarComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class UsuarioRoutingModule{}
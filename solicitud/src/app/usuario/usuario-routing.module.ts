import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { SolGuardService as guard } from '../auth/auth.guard';
import { UsuarioEditarComponent } from "./usuario-editar.component";
import { UsuarioListarComponent } from "./usuario-listar.component";

@NgModule({
    imports: [

        RouterModule.forChild([
            {
                path: 'usuario' , 
                component : UsuarioListarComponent,
                canActivate: [guard],
                data: { expectedRol: ['user']}
            },
            {
                path: 'usuario/:id/editar', 
                component: UsuarioEditarComponent, 
                canActivate: [guard], 
                data: { expectedRol: ['user']} 
            }
        ])
    ],
    exports: [RouterModule]
})
export class UsuarioRoutingModule{}
import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { SolGuardService as guard } from '../auth/auth.guard';
import { UserEditComponent } from "./user-edit.component";
import { UserListComponent } from "./user-list.component";
import { UserResolver } from "./user-resolver.component";

@NgModule({
    imports: [

        RouterModule.forChild([
            {
                path: '' , 
                canActivate: [guard],
                data: { expectedRol : ['admin']},
                component : UserListComponent
            },
            {
                path: ':id/editar', 
                canActivate: [guard],
                resolve: { resolvedData: UserResolver},
                data: { expectedRol : ['admin']},
                component: UserEditComponent
            }
        ])
    ],
    exports: [RouterModule]
})
export class UserRoutingModule{}
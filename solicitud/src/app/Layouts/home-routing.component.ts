import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { SolGuardService as guard } from '../auth/auth.guard';
import { HomeLayoutComponent } from "./home-layout.component";

@NgModule({
    imports: [

        RouterModule.forChild([
            {
                path: '',
                component: HomeLayoutComponent,
                canActivate: [guard],
                data: { expectedRol: ['admin', 'user' ,'director']},
                children: [
                    {
                        path: 'solicitud',
                        loadChildren: () => import('../solicitud/solicitud.module').then(m => m.SolicitudModule)
                      },
                      {
                        path: 'estudio',
                        loadChildren: () => import('../estudio/estudio.module').then(m => m.EstudioModule)      
                      },
                      {
                        path: 'consulta',
                        loadChildren: () => import('../consulta/consulta.module').then(m => m.ConsultaModule)
                      },
                      {
                        path: 'usuario',
                        loadChildren: () => import('../usuario/usuario.module').then(m => m.UsuarioModule)
                      },
                      {
                        path: 'configuracion',
                        loadChildren: () => import('../configuracion/configuracion.module').then(m => m.ConfiguracionModule)
                      },
                      { path: '', redirectTo: 'solicitud', pathMatch: 'full'}
                ]
            }
        ])
    ],
    exports: [RouterModule]
})
export class HomeRoutingModule { }
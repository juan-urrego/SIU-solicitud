import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { SolGuardService as guard } from '../auth/auth.guard';
import { SolicitudEditarDetalleComponent } from "./solicitud-editar/solicitud-editar-detalle.component";
import { SolicitudEditarInfoComponent } from "./solicitud-editar/solicitud-editar-info.component";
import { SolicitudEditarPrecotizacionComponent } from "./solicitud-editar/solicitud-editar-precotizacion.component";
import { SolicitudEditarProyectoComponent } from "./solicitud-editar/solicitud-editar-proyecto.component";
import { SolicitudEditarComponent } from "./solicitud-editar/solicitud-editar.component";
import { SolicitudEditarGuard } from "./solicitud-editar/solicitud-editar.guard";
import { SolicitudListarComponent } from "./solicitud-listar.component";
import { SolicitudResolver } from "./solicitud-resolver.service";

@NgModule({
    imports: [

        RouterModule.forChild([
            {
                path: '',
                component: SolicitudListarComponent
            },
            {
                path: ':id/editar',
                component: SolicitudEditarComponent,
                resolve: { resolvedData: SolicitudResolver},
                canDeactivate: [SolicitudEditarGuard],
                children: [
                    {
                        path: '',
                        redirectTo: 'info',
                        pathMatch: 'full'
                    },
                    {
                        path: 'info',
                        component: SolicitudEditarInfoComponent
                    },
                    {
                        path: 'proyecto',
                        component: SolicitudEditarProyectoComponent
                    },
                    {
                        path: 'detalle',
                        component: SolicitudEditarDetalleComponent
                    },
                    {
                        path: 'precotizacion',
                        component: SolicitudEditarPrecotizacionComponent
                    }
                ]
            }


        ])
    ],
    exports: [RouterModule]
})
export class SolicitudRoutingModule { }
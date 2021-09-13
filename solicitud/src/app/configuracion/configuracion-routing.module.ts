import { NgModule } from "@angular/core";
import { RouterModule } from '@angular/router';
import { ConfiguracionComponent } from "./configuracion.component";
import { GrupoComponent } from "./grupo/grupo.component";
import { InvestigadorComponent } from "./investigador/investigador.component";
import { LineaGeneralComponent } from "./linea-general/linea-general.component";
import { ProveedorComponent } from "./proveedor/proveedor.component";
import { ProyectoComponent } from "./proyecto/proyecto.component";

@NgModule({
    imports: [

        RouterModule.forChild([
            { path: '', component: ConfiguracionComponent },
            { path: 'grupo', component: GrupoComponent },            
            { path: 'investigador', component: InvestigadorComponent },            
            { path: 'proveedor', component: ProveedorComponent },
            { path: 'lineaGeneral', component: LineaGeneralComponent },
            { path: 'proyecto', component: ProyectoComponent },
            {
                path: 'parametros',
                loadChildren: () => import('./parametro/parametro.module').then(m => m.ParametroModule)
            }
        ])
    ],
    exports: [RouterModule]
})
export class ConfiguracionRoutingModule { }
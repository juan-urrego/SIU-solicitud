import { NgModule } from '@angular/core';
import { ConfiguracionRoutingModule } from './configuracion-routing.module';

import { GrupoComponent } from './grupo/grupo.component';
import { InvestigadorComponent } from './investigador/investigador.component';
import { ProveedorComponent } from './proveedor/proveedor.component';
import { ProveedorEditarComponent } from './proveedor/proveedor-editar.component';
import { SharedModule } from '../shared/shared.module';
import { ConfiguracionComponent } from './configuracion.component';
import { LineaGeneralComponent } from './linea-general/linea-general.component';
import { ProyectoComponent } from './proyecto/proyecto.component';



@NgModule({
  declarations: [
    //components
    ConfiguracionComponent,
    GrupoComponent,    
    InvestigadorComponent,    
    ProveedorComponent,
    ProveedorEditarComponent,
    LineaGeneralComponent,
    ProyectoComponent
  ],
  imports: [
    //routes
    ConfiguracionRoutingModule,
    SharedModule
  ]
})
export class ConfiguracionModule { }

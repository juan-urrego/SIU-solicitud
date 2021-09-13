import { NgModule } from '@angular/core';
import { SolicitudListarComponent } from './solicitud-listar.component';
import { SolicitudEditarComponent } from './solicitud-editar/solicitud-editar.component';
import { SolicitudRoutingModule } from './solicitud-routing.module';
import { SharedModule } from '../shared/shared.module';
import { SolicitudEditarInfoComponent } from './solicitud-editar/solicitud-editar-info.component';
import { SolicitudEditarProyectoComponent } from './solicitud-editar/solicitud-editar-proyecto.component';
import { SolicitudEditarDetalleComponent } from './solicitud-editar/solicitud-editar-detalle.component';
import { SolicitudEditarPrecotizacionComponent } from './solicitud-editar/solicitud-editar-precotizacion.component';



@NgModule({
  declarations: [
    //components
    SolicitudListarComponent,
    SolicitudEditarComponent,
    SolicitudEditarInfoComponent,
    SolicitudEditarProyectoComponent,
    SolicitudEditarDetalleComponent,
    SolicitudEditarPrecotizacionComponent
  ],
  imports: [
    //routes
    SolicitudRoutingModule,
    SharedModule
  ]
})
export class SolicitudModule { }

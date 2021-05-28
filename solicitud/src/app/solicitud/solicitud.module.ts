import { NgModule } from '@angular/core';
import { SolicitudListarComponent } from './solicitud-listar.component';
import { SolicitudEditarComponent } from './solicitud-editar.component';
import { SolicitudRoutingModule } from './solicitud-routing.module';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [
    //components
    SolicitudListarComponent,
    SolicitudEditarComponent
  ],
  imports: [
    //routes
    SolicitudRoutingModule,
    SharedModule
  ]
})
export class SolicitudModule { }

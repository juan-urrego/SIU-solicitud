import { NgModule } from "@angular/core";
import { ConsultaListarComponent } from "./consulta-listar.component";
import { ConsultaEditarComponent } from "./consulta-editar.component";
import { ConsultaRoutingModule } from "./consulta-routing.module";
import { SharedModule } from "../shared/shared.module";

@NgModule({
    declarations: [
      //components
      ConsultaListarComponent,
      ConsultaEditarComponent
    ],
    imports: [
      //routes
      ConsultaRoutingModule,
      SharedModule
    ]
  })
  export class ConsultaModule { }
  
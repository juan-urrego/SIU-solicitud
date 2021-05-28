import { NgModule } from "@angular/core";
import { EstudioListarComponent } from "./estudio-listar.component";
import { EstudioEditarComponent } from "./estudio-editar.component";
import { EstudioRoutingModule } from "./estudio-routing.module";
import { SharedModule } from "../shared/shared.module";

@NgModule({
    declarations: [
      //components
      EstudioListarComponent,
      EstudioEditarComponent
    ],
    imports: [
      //routes
      EstudioRoutingModule,
      SharedModule
    ]
  })
  export class EstudioModule { }
  
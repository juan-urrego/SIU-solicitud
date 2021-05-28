import { NgModule } from "@angular/core";
import { SharedModule } from "../shared/shared.module";
import { UsuarioEditarComponent } from "./usuario-editar.component";
import { UsuarioListarComponent } from "./usuario-listar.component";
import { UsuarioRoutingModule } from "./usuario-routing.module";

@NgModule({
    declarations:[
        UsuarioListarComponent,
        UsuarioEditarComponent
    ],    
    imports:[
        UsuarioRoutingModule,
        SharedModule
    ]
})
export class UsuarioModule {}
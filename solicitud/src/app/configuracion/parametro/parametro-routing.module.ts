import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { ParametroComponent } from "./parametro.component";

@NgModule({
    imports: [
        RouterModule.forChild([
            { path: '', component: ParametroComponent}
        ])
    ],
    exports: [RouterModule]
})
export class ParametroRoutingModule{}
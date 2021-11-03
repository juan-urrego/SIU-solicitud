import { NgModule } from "@angular/core";
import { SharedModule } from "../shared/shared.module";
import { UserEditComponent } from "./user-edit.component";
import { UserListComponent } from "./user-list.component";
import { UserRoutingModule } from "./user-routing.module";

@NgModule({
    declarations:[
        UserListComponent,
        UserEditComponent
    ],    
    imports:[
        UserRoutingModule,
        SharedModule
    ]
})
export class UserModule {}
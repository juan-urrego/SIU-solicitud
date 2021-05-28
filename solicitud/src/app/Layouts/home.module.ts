import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SidebarModule } from 'ng-sidebar';
import { SharedModule } from '../shared/shared.module';
import { HomeLayoutComponent } from './home-layout.component';
import { HomeRoutingModule } from './home-routing.component';



@NgModule({
  declarations: [
    //components
    HomeLayoutComponent
  ],
  imports: [
    //routes
    HomeRoutingModule,
    NgbModule,
    SharedModule,
    SidebarModule.forRoot()
  ],
})
export class HomeModule { }

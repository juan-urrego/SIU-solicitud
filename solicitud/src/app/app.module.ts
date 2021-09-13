import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import  {MatCurrencyFormatModule} from 'mat-currency-format';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap'
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';

import { CurrencyPipe } from '@angular/common';
import { interceptorProvider } from './shared/interceptors/sol-interceptor.service';
import { SidebarModule } from 'ng-sidebar';
import { HomeModule } from './Layouts/home.module';
import { UserModule } from './auth/user.module';


@NgModule({
  declarations: [	
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    SidebarModule.forRoot(),
    NgbModule,
    HomeModule,
    SharedModule,
    UserModule,
    AppRoutingModule
  ],
  providers: [CurrencyPipe, interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }

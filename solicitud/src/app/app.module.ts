import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import  {MatCurrencyFormatModule} from 'mat-currency-format';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SolicitudComponent } from './components/solicitud/solicitud.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { GrupoComponent } from './components/settings/grupo/grupo.component';
import { GrupoEditarComponent } from './components/settings/grupo/grupo-editar.component';
import { InvestigadorComponent } from './components/settings/investigador/investigador.component';
import { InvestigadorEditarComponent } from './components/settings/investigador/investigador-editar.component';
import { EstudioPrevioComponent } from './components/estudio-previo/estudio-previo.component';
import { EstudioPrevioEditarComponent } from './components/estudio-previo/estudio-previo-editar.component';
import { ConsultaEditarComponent } from './components/consulta-precios/consulta-editar.component';
import { ConsultaComponent } from './components/consulta-precios/consulta.component';

import { ProveedorComponent } from './components/settings/proveedor/proveedor.component';

import { ProveedorEditarComponent } from './components/settings/proveedor/proveedor-editar.component';
import { SolicitudEditarComponent } from './components/solicitud/solicitud-editar.component';

import { CurrencyPipe } from '@angular/common';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { interceptorProvider } from './interceptors/sol-interceptor.service';
import { AuxiliarEditarComponent } from './components/auxiliar/auxiliar-editar.component';
import { AuxiliarComponent } from './components/auxiliar/auxiliar.component';

@NgModule({
  declarations: [	
    AppComponent,
    SolicitudComponent,
    SolicitudEditarComponent,
    EstudioPrevioComponent,
    EstudioPrevioEditarComponent,
    GrupoComponent,
    ConsultaComponent,
    ConsultaEditarComponent,
    GrupoEditarComponent,
    InvestigadorComponent,
    InvestigadorEditarComponent,
    AuxiliarEditarComponent,
    AuxiliarComponent,
    ProveedorComponent,

    ProveedorEditarComponent,

    LoginComponent,

    RegistroComponent
   ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatCurrencyFormatModule,
    BrowserAnimationsModule
  ],
  providers: [CurrencyPipe, interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }

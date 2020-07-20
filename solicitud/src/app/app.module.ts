import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import  {MatCurrencyFormatModule} from 'mat-currency-format';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SolicitudComponent } from './solicitud/solicitud.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { GrupoComponent } from './grupo/grupo.component';
import { GrupoEditarComponent } from './grupo/grupo-editar.component';
import { InvestigadorComponent } from './investigador/investigador.component';
import { InvestigadorEditarComponent } from './investigador/investigador-editar.component';
import { EstudioPrevioComponent } from './estudio-previo/estudio-previo.component';
import { EstudioPrevioEditarComponent } from './estudio-previo/estudio-previo-editar.component';
import { ConsultaEditarComponent } from './consulta-precios/consulta-editar.component';
import { ConsultaComponent } from './consulta-precios/consulta.component';

import { ProveedorComponent } from './proveedor/proveedor.component';

import { ProveedorEditarComponent } from './proveedor/proveedor-editar.component';
import { SolicitudEditarComponent } from './solicitud/solicitud-editar.component';

import { CurrencyPipe } from '@angular/common';

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
    
    ProveedorComponent,
    
    ProveedorEditarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatCurrencyFormatModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
    BsDropdownModule.forRoot()
  ],
  providers: [CurrencyPipe],
  bootstrap: [AppComponent]
})
export class AppModule { }

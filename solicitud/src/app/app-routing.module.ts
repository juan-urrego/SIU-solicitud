import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SolicitudComponent } from './components/solicitud/solicitud.component';
import { GrupoComponent } from './components/settings/grupo/grupo.component';

import { GrupoEditarComponent } from './components/settings/grupo/grupo-editar.component';
import { InvestigadorComponent } from './components/settings/investigador/investigador.component';

import { InvestigadorEditarComponent } from './components/settings/investigador/investigador-editar.component';
import { ProveedorEditarComponent } from './components/settings/proveedor/proveedor-editar.component';
import { ProveedorComponent } from './components/settings/proveedor/proveedor.component';


import { SolicitudEditarComponent } from './components/solicitud/solicitud-editar.component';
import { EstudioPrevioComponent } from './components/estudio-previo/estudio-previo.component';
import { EstudioPrevioEditarComponent } from './components/estudio-previo/estudio-previo-editar.component';

import { ConsultaEditarComponent } from './components/consulta-precios/consulta-editar.component';
import { ConsultaComponent } from './components/consulta-precios/consulta.component';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { SolGuardService as guard } from './guards/sol-guard.service';
import { AuxiliarComponent } from './components/auxiliar/auxiliar.component';
import { AuxiliarEditarComponent } from './components/auxiliar/auxiliar-editar.component';



const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot([
    { path: '', component: SolicitudComponent, canActivate: [guard], data: { expectedRol: ['admin','user']}},
    { path: 'solicitud/:id/editar', component: SolicitudEditarComponent, canActivate: [guard], data: { expectedRol: ['admin','user']} },
    { path: 'estudio', component: EstudioPrevioComponent, canActivate: [guard], data: { expectedRol: ['admin','user']} },
    { path: 'estudio/:id/editar', component: EstudioPrevioEditarComponent, canActivate: [guard], data: { expectedRol: ['admin','user']} },
    { path: 'consulta', component: ConsultaComponent, canActivate: [guard], data: { expectedRol: ['admin','user']} },
    { path: 'consulta/:id/editar', component: ConsultaEditarComponent, canActivate: [guard], data: { expectedRol: ['admin','user']} },
    { path: 'grupo', component: GrupoComponent, canActivate: [guard], data: { expectedRol: ['admin','user']} },
    { path: 'grupo/:id/editar', component: GrupoEditarComponent, canActivate: [guard], data: { expectedRol: ['admin','user']} },
    { path: 'investigador', component: InvestigadorComponent, canActivate: [guard], data: { expectedRol: ['admin','user']} },
    { path: 'investigador/:id/editar', component: InvestigadorEditarComponent, canActivate: [guard], data: { expectedRol: ['admin','user']} },
    { path: 'proveedor', component: ProveedorComponent, canActivate: [guard], data: { expectedRol: ['admin','user']} },
    { path: 'proveedor/:id/editar', component: ProveedorEditarComponent, canActivate: [guard], data: { expectedRol: ['admin','user']} },
    { path: 'auxiliar', component: AuxiliarComponent, canActivate: [guard], data: { expectedRol: ['admin']} },
    { path: 'auxiliar/:id/editar', component: AuxiliarEditarComponent, canActivate: [guard], data: { expectedRol: ['admin']} },
    { path: 'login', component: LoginComponent },
    { path: 'registro', component: RegistroComponent, canActivate: [guard], data: { expectedRol: ['admin']} },
    { path: '**', redirectTo: '', pathMatch: 'full' },
    { path: '', redirectTo: 'solicitud', pathMatch: 'full' }
  ])],
  exports: [RouterModule]
})
export class AppRoutingModule { }

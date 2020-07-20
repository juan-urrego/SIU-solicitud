import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SolicitudComponent } from './solicitud/solicitud.component';
import { GrupoComponent } from './grupo/grupo.component';

import { GrupoEditarComponent } from './grupo/grupo-editar.component';
import { InvestigadorComponent } from './investigador/investigador.component';

import { InvestigadorEditarComponent } from './investigador/investigador-editar.component';
import { ProveedorEditarComponent } from './proveedor/proveedor-editar.component';
import { ProveedorComponent } from './proveedor/proveedor.component';



import { SolicitudEditarComponent } from './solicitud/solicitud-editar.component';
import { EstudioPrevioComponent } from './estudio-previo/estudio-previo.component';
import { EstudioPrevioEditarComponent } from './estudio-previo/estudio-previo-editar.component';

import { ConsultaEditarComponent } from './consulta-precios/consulta-editar.component';
import { ConsultaComponent } from './consulta-precios/consulta.component';


const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot([
    {path: 'solicitud' , component: SolicitudComponent},
    {
      path: 'solicitud/:id/editar',
      component: SolicitudEditarComponent
    },
    {path: 'estudio' , component: EstudioPrevioComponent},
    {
      path: 'estudio/:id/editar',
      component: EstudioPrevioEditarComponent
    },
    {path: 'consulta' , component: ConsultaComponent},
    {
      path: 'consulta/:id/editar',
      component: ConsultaEditarComponent
    },
    {path: 'grupo' , component: GrupoComponent},    
    {
      path: 'grupo/:id/editar',
      component: GrupoEditarComponent
    },
    {path: 'investigador' , component: InvestigadorComponent},
    
    {
      path: 'investigador/:id/editar',
      component: InvestigadorEditarComponent
    },
    {path: 'proveedor' , component: ProveedorComponent},
  
    {
      path: 'proveedor/:id/editar',
      component: ProveedorEditarComponent
    },
    {path: '' , redirectTo: 'solicitud', pathMatch: 'full'},
    {path: '**' , redirectTo: 'solicitud' , pathMatch: 'full'}
  ])],
  exports: [RouterModule]
})
export class AppRoutingModule { }

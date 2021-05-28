import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SolGuardService as guard } from './auth/auth.guard';



const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot([
    { 
      path: '',
      canActivate: [guard],
      data: { expectedRol: ['admin','user']},
      loadChildren: () => import('./Layouts/home.module').then(m => m.HomeModule)
    },
    { path: '**', redirectTo: 'home', pathMatch: 'full' }
  ])],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanDeactivate } from '@angular/router';
import { Observable } from 'rxjs';

import { SolicitudEditarComponent } from './solicitud-editar.component';

@Injectable({
  providedIn: 'root'
})
export class SolicitudEditarGuard implements CanDeactivate<SolicitudEditarComponent>  {

  canDeactivate(component: SolicitudEditarComponent,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
    if (component.isDirty) {
      const productName = component.solicitudForm.get('nombreProyecto') || 'Nueva solicitud';
      return confirm(`Navigate away and lose all changes to ${productName}?`);
    }
    return true;
  }


}

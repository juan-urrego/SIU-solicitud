import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class SolGuardService implements CanActivate {

  realRol: string;

  constructor(private authService: AuthService,
    private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
      const expectedRol = route.data.expectedRol;
      this.realRol = this.authService.isAdmin() ? 'admin' : 'user';
      
      if (!this.authService.isLogged() || expectedRol.indexOf(this.realRol) === -1) {
        this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
        return false
      }
      return true;

    }
}

import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../services/login/auth.service';

@Injectable({
  providedIn: 'root'
})
export class SolGuardService implements CanActivate {

  realRol: string;

  constructor(private authService: AuthService,
    private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
      const roles = this.authService.getAuthorities();
      const currentUser = this.authService.currentUserValue;
    
      this.realRol = 'user';
      roles.forEach(rol => {
        if(rol === 'ROLE_ADMIN'){
          this.realRol = 'admin';
        }
      });

      if (currentUser) {
        return true;
      }
      
      this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
      return false

    }
}

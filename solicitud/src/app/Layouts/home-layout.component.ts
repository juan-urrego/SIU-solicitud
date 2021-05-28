import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-home-layout',
  templateUrl: 'home-layout.component.html',
  styleUrls: ['./home-layout.Component.css']
})
export class HomeLayoutComponent {
  _opened: boolean = false;


  constructor(private authService: AuthService,
    private router: Router) {
      console.log(this.authService.currentUserValue);
      
    }

    toggleMenu() {
      this._opened = !this._opened;
    }

  onLogOut() {
    this.authService.logOut();
    this.router.navigate(['/login']);
  }
}
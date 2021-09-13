import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-home-layout',
  templateUrl: 'home-layout.component.html',
  styleUrls: ['./home-layout.Component.css']
})
export class HomeLayoutComponent implements OnInit {
  _opened: boolean = true;
  isAdmin: boolean;
  isDirector: boolean;
  rol: string;
  userName: string;

  constructor(private authService: AuthService,
    private router: Router) {
      this.isAdmin = this.authService.isAdmin();
      this.rol = this.authService.getRole();
      this.userName = this.authService.getEmail();
    }

    ngOnInit(){

    }

    toggleMenu() {
      this._opened = !this._opened;
    }

    onLogOut() {
      this.authService.logOut();
      this.router.navigate(['/login']);
    }
}
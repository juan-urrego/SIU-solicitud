import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtDto } from './models/login/jwt-dto';
import { AuthService } from './services/login/auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: JwtDto;

  constructor(private authService: AuthService,
    private router: Router) {

    this.authService.currentUser.subscribe(x => this.currentUser = x);
  }


  onLogOut() {
    this.authService.logOut();
    this.router.navigate(['/login']);
  }

}

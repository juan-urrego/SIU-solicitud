import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtDto } from './shared/models/login/jwt-dto';
import { AuthService } from './auth/auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public isCollapsed = false;
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

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from './services/login/token.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{

  isLogged = false;

  constructor(private tokenService: TokenService,
              private router: Router){

  }

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.isLogged = true;
    }
    else {
      this.isLogged = false;
    }
  }

  onLogOut(){
    this.tokenService.logOut();
    window.location.reload();
    
  }

 
}

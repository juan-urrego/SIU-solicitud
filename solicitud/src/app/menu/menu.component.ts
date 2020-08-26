import { Component, OnInit } from '@angular/core';
import { TokenService } from '../services/login/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
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

import { Component, OnInit } from '@angular/core';
import { TokenService } from '../services/login/token.service';
import { AuthService } from '../services/login/auth.service';
import { Router } from '@angular/router';
import { LoginUsuario } from '../models/login/login-usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  isLogged = false;
  isLoginFail = false;
  loginUsuario: LoginUsuario;
  email: string;
  password: string;
  roles: string[] = [];
  errMensaje: string;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {

    if (this.tokenService.getToken()){
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();
    }
  }

  onLogin(): void{
    this.loginUsuario = new LoginUsuario(this.email, this.password);
    this.authService.login(this.loginUsuario).subscribe(
      data =>{
        this.isLogged = true;
        this.isLoginFail = false;
        this.tokenService.setToken(data.token);
        this.tokenService.setEmail(data.email);
        this.tokenService.setAuthorities(data.authorities);
        this.roles = data.authorities;
        this.router.navigate(['/solicitud'])
      },
      err => {
        this.isLogged = false;
        this.isLoginFail = true;
        this.errMensaje = err.error.mensaje;
        
        
      }
    );
  }

}

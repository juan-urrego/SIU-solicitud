import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/login/auth.service';
import { UserService } from '../services/login/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginUsuario } from '../models/login/login-usuario';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  isLogged = false;
  isLoginFail = false;
  loginUsuario: LoginUsuario;
  roles: string[] = [];
  errMensaje: string;
  loginForm : FormGroup;
  submitted = false;
  loading = false;
  returnUrl : string;

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route : ActivatedRoute,
    private fb: FormBuilder
  ) { 

    if (this.authService.currentUserValue){
      this.router.navigate(['/']);
    }
  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password : ['', [Validators.required]]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onLogin(): void{
    this.submitted = true;

    if(this.loginForm.invalid) {
      return;
    }
    this.loading = true;

    this.loginUsuario = new LoginUsuario(this.loginForm.get('email').value, this.loginForm.get('password').value);
    this.authService.login(this.loginUsuario).pipe(first()).subscribe(
      data =>{
        this.roles = data.authorities;
        this.router.navigate([this.returnUrl]);
      },
      err => {
        this.isLoginFail = true;
        this.errMensaje = err.error.mensaje;
        this.loading = false;
      }
    );
  }

}

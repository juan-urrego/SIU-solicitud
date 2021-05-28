import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginUsuario } from '../shared/models/login/login-usuario';
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
    private authService: AuthService,
    private router: Router,
    private route : ActivatedRoute,
    private fb: FormBuilder
  ) { 
    const user = this.authService.getToken();
    if (user){
      this.router.navigate(['/solicitud']);
      console.log("deberia irmeeeeeee con token");
      console.log("este es el token");
      console.log(this.authService.getToken());
      
      
      console.log(this.authService.currentUserValue);
      
    }
  }
  
  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password : ['', [Validators.required]]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/solicitud';
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

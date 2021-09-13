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
    if (this.authService.getToken()){
      this.router.navigate(['/solicitud']);
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
    this.authService.login(this.loginUsuario).subscribe(
      data =>{
        this.authService.setToken(data.token);
        this.router.navigate([this.returnUrl]);
      },
      err => {
        this.errMensaje = err.error.message;
        this.loading = false;
      }
    );
  }

}

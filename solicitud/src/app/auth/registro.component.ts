import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NuevoUsuario } from '../models/login/nuevo-usuario';
import { AuthService } from '../services/login/auth.service';
import { TokenService } from '../services/login/token.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html'
})
export class RegistroComponent implements OnInit {

  isLogged = false;
  isRegister = false;
  isRegisterFail = false;
  nuevoUsuario: NuevoUsuario;
  nombre: string;
  apellido: string;
  email: string;
  password: string;

  errMensaje: string;

  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }
  }

  onRegister(): void{
    this.nuevoUsuario = new NuevoUsuario(this.nombre, this.apellido, this.email, this.password);
    this.authService.nuevo(this.nuevoUsuario).subscribe(
      data =>{
        this.isRegister = true
        this.isRegisterFail = false;
        console.log("cuenta creada");
        this.router.navigate(['/solicitud'])
      },
      err => {
        this.isRegister = false;
        this.isRegisterFail = true;
        
        this.errMensaje = err.error.mensaje;      
        console.log(this.errMensaje);
      }
    );
  }

}

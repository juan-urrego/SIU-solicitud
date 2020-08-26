import { Component, OnInit } from '@angular/core';
import { NuevoUsuario } from 'src/app/models/login/nuevo-usuario';
import { FormGroup, FormBuilder, Validators, EmailValidator } from '@angular/forms';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/login/auth.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  templateUrl: './auxiliar-editar.component.html'
})
export class AuxiliarEditarComponent implements OnInit {

  title: string;
  mensajeError: string;
  auxiliarForm: FormGroup;
  auxiliar: NuevoUsuario;
  id: number;
  private sub: Subscription;


  constructor(private fb: FormBuilder,
      private auxiliarService: AuthService,
      private router: Router,
      private route: ActivatedRoute) { }

  ngOnInit() {
      this.auxiliarForm = this.fb.group({
          nombre: ['', [Validators.required, Validators.minLength(3)]],
          apellido: ['', [Validators.required]],
          email: ['', [Validators.required, Validators.email]],
          password: ['', [Validators.required]]
      });

      this.sub = this.route.paramMap.subscribe(
          params => {
              const id = +params.get('id');
              this.id = id;
              if (id != 0){

                this.getAuxiliar(id);
              }
          }
      );
  }

  ngOnDestroy(): void {
      this.sub.unsubscribe();
  }


  getAuxiliar(id: number) {
      this.auxiliarService.getAuxiliar(id)
          .subscribe({
              next: (auxiliar: NuevoUsuario) => this.displayAuxiliar(auxiliar),
              error: err => this.mensajeError = err
          });
  }
  
  displayAuxiliar(auxiliar: NuevoUsuario): void {
      if (this.auxiliarForm) {
          this.auxiliarForm.reset();
      }
      this.auxiliar = auxiliar;

      if (this.id === 0) {
          this.title = "Agregar Auxiliar";
      } else {
          this.title = `Editar Auxiliar: ${this.auxiliar.nombre} ${this.auxiliar.apellido}`;
      }

      this.auxiliarForm.patchValue({
          nombre: this.auxiliar.nombre,
          apellido: this.auxiliar.apellido,
          email: this.auxiliar.email
      });
      
  }


  deleteAuxiliar(): void {
      if (this.auxiliar.id === 0) {
          this.onSaveComplete();
      } else {
          if (confirm(`Realmente desea eliminar el proveedor: ${this.auxiliar.nombre}  ${this,this.auxiliar.apellido}?`)) {
              this.auxiliarService.delete(this.auxiliar.id)
                  .subscribe({
                      next: () => this.onSaveComplete(),
                      error: err => this.mensajeError = err
                  });
          }
      }
  }

  onSaveComplete(): void {
      this.auxiliarForm.reset();
      this.router.navigate(['/auxiliar']);
  }

  saveAuxiliar(): void {
      console.log("en el boton");
      
      if (this.auxiliarForm.valid) {
          if (this.auxiliarForm.dirty) {
              const p = { ...this.auxiliar, ...this.auxiliarForm.value };
              console.log(p);
              
              if (p.id === 0) {
                  this.auxiliarService.nuevo(p)
                      .subscribe({
                          next: () => this.onSaveComplete(),
                          error: err => this.mensajeError = err
                      });
              } else {
                  this.auxiliarService.update(p.id,p)
                      .subscribe({
                          next: () => this.onSaveComplete(),
                          error: err => this.mensajeError = err
                      });
              }
          } else {
              this.onSaveComplete();
          }
      } else {
          this.mensajeError = 'Verificar los errores de validacion'
      }
  }

}

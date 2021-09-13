import { Injectable } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Usuario, UsuarioResolved } from './usuario';
import { UserService } from './usuario.service';

@Injectable({ providedIn: 'root' })
export class UsuarioResolver implements Resolve<UsuarioResolved> {

    usuarioForm: FormGroup;

    get roles(): FormArray {
        return this.usuarioForm.get('roles') as FormArray;
      }

    constructor(private fb: FormBuilder,
                private usuarioService: UserService){

    }

    resolve(route: ActivatedRouteSnapshot): Observable<UsuarioResolved> {
        const id = route.paramMap.get('id');
        this.usuarioForm = this.fb.group({
            nombre: ['', [Validators.required, Validators.minLength(3)]],
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required]],
            cargo: ['', Validators.required],
            roles: this.fb.array([])
        });

        if (isNaN(+id)) {
            const message = `Product id was not a number: ${id}`;
            console.error(message);
            return of({usuario: null, error: message, form: this.usuarioForm});
        }

        if (+id == 0) {
            this.addTag();
            return of({usuario: null , form: this.usuarioForm});
        }
        return this.usuarioService.getUser(+id)
            .pipe(
                map(usuario => ({usuario: usuario, form: this.displayForm(usuario)}))
            );
    }

    displayForm(usuario: Usuario): FormGroup {
        this.usuarioForm.patchValue({
            nombre: usuario.nombre,
            email: usuario.email,
            cargo: usuario.cargo
        });
        
        usuario.roles.forEach(rol => {
            if(rol.rolNombre == 'ROLE_USER'){
                this.roles.push(new FormControl('usuario'));
            }
            if(rol.rolNombre == 'ROLE_ADMIN'){
                this.roles.push(new FormControl('admin'));
            }
            if(rol.rolNombre == 'ROLE_DIRECTOR'){
                this.roles.push(new FormControl('director'));
            }
            
        });

        return this.usuarioForm;
    }

    addTag(): void {
        this.roles.push(new FormControl());
      }
    
      deleteTag(index: number): void {
        this.roles.removeAt(index);
        this.roles.markAsDirty();
      }
}
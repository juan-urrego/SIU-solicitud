import { Injectable } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { User, UserResolved } from './user';
import { UserService } from './user.service';

@Injectable({ providedIn: 'root' })
export class UserResolver implements Resolve<UserResolved> {

    userForm: FormGroup;

    get roles(): FormArray {
        return this.userForm.get('roles') as FormArray;
      }

    constructor(private fb: FormBuilder,
                private usuarioService: UserService){

    }

    resolve(route: ActivatedRouteSnapshot): Observable<UserResolved> {
        const id = route.paramMap.get('id');
        this.userForm = this.fb.group({
            name: ['', [Validators.required, Validators.minLength(3)]],
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required]],
            position: ['', Validators.required],
            roles: this.fb.array([])
        });

        if (isNaN(+id)) {
            const message = `User id was not a number: ${id}`;
            console.error(message);
            return of({user: null, error: message, form: this.userForm});
        }

        if (+id == 0) {
            this.addTag();
            return of({user: null , form: this.userForm});
        }
        return this.usuarioService.getUser(+id)
            .pipe(
                map(user => ({user: user, form: this.displayForm(user)}))
            );
    }

    displayForm(user: User): FormGroup {
        this.userForm.patchValue({
            name: user.name,
            email: user.email,
            position: user.position
        });
        
        user.roles.forEach(rol => {
            if(rol.roleName == 'ROLE_USER'){
                this.roles.push(new FormControl('usuario'));
            }
            if(rol.roleName == 'ROLE_ADMIN'){
                this.roles.push(new FormControl('admin'));
            }
            if(rol.roleName == 'ROLE_DIRECTOR'){
                this.roles.push(new FormControl('director'));
            }
            
        });
        this.userForm.get('roles').disable();
        return this.userForm;
    }

    addTag(): void {
        this.roles.push(new FormControl());
      }

}
import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Estudio } from 'src/app/estudio/estudio';
import { EstudioService } from 'src/app/estudio/estudio.service';
import { AuthService } from '../auth/auth.service';
import { ParametroAcuerdoService } from '../configuracion/parametro/acuerdo/parametroAcuerdo.service';
import { Parametro } from '../configuracion/parametro/parametro';
import { UnidadAcademicaService } from '../configuracion/parametro/unidad-academica/unidadAcademica.service';
import { User } from '../user/user';
import { UserService } from '../user/user.service';

@Component({
    templateUrl: 'estudio-editar.component.html'
})

export class EstudioEditarComponent implements OnInit {
    mensajeError: string;
    title: string;
    rol: string;
    urlUser: any = null;
    urlDirector: any = null;
    estudioForm: FormGroup;
    estudio: Estudio;
    unidadesAcademicas: Parametro[];
    emailLogged: string = '';


    get detalleTramiteDtos(): FormArray {
        return this.estudioForm.get('_detalleTramiteDtos') as FormArray;
    }

    constructor(private estudioService: EstudioService,
        private router: Router,
        private route: ActivatedRoute,
        private authService: AuthService,
        private usuarioService: UserService,
        private unidadAcademicaService: UnidadAcademicaService) { 
            this.rol = this.authService.getRole();
            this.emailLogged = this.authService.getEmail();
        }

    ngOnInit() { 
        this.route.data.subscribe(data => {            
            this.estudio = data['resolvedData'].estudio;
            this.estudioForm = data['resolvedData'].form;
        });
        this.getUnidadesAcademicas();
        if (this.estudio.firmaDirector){
            this.getFirma(this.estudio.director.id, "ROLE_DIRECTOR");
        }
        if (this.estudio.firmaUsuario){
            this.getFirma(this.estudio.solicitud.user.id, "ROLE_USER");
        }
    }

    getUnidadesAcademicas() {
        return this.unidadAcademicaService.getUnidadAcademicas().subscribe({
            next: parametros => {
                this.unidadesAcademicas = parametros;
                //VERIFICAR QUE AL MENOS HAY UNA UNIDAD ACADEMICA
                if(this.unidadesAcademicas.length > 0){
                    this.estudioForm.controls['unidadAcademica'].setValue(this.unidadesAcademicas[0]);
                }
            },
            error: error => this.mensajeError = error
        })
    }

    getFirma(id: number, rol: string) {
        this.usuarioService.getUserFirma(id).subscribe({
            next: image => this.createImageFromBlob(image, rol),
            error: error => this.mensajeError = error
        });
    }

    createImageFromBlob(image: Blob, rol: string) {
        let reader = new FileReader();
        console.log(rol);
        
        reader.addEventListener("load", () => {
            if(rol != 'ROLE_DIRECTOR'){
                this.urlUser = reader.result;
            }else{
                this.urlDirector = reader.result
            }
        }, false);
        if (image) {
            reader.readAsDataURL(image);
        }
        console.log(this.urlUser);
    }


    signDirector(): void {
        if(!this.estudio.firmaDirector){
            this.estudioForm.get('firmaDirector').setValue(true);
            const p = {...this.estudio, ...this.estudioForm.value}
            this.estudioService.updateEstudio(p).subscribe({
                next: () => this.getFirma(this.estudio.director.id, this.rol),
                error: error => this.mensajeError = error
            });
        }
    }

    signUser(): void{
        if(!this.estudio.firmaUsuario){
            this.estudioForm.get('firmaUsuario').setValue(true);
            const p = {...this.estudio, ...this.estudioForm.value}
            this.estudioService.updateEstudio(p).subscribe({
                next: () => this.getFirma(this.estudio.solicitud.user.id, this.rol),
                error: error => this.mensajeError = error
            });
        }
    }



    onSaveComplete(): void{
        this.estudioForm.reset();
        this.router.navigate(['/estudio']);
    }

    isUserSignable(): boolean { 
        if(this.estudio.firmaUsuario){
            return false;
        }
        if(this.estudio.solicitud.user.email == this.emailLogged){
            return true;
        }
        return false;
    }

    isDirectorSignable(): boolean { 
        if(this.estudio.firmaDirector){
            return false;
        }
        if(this.estudio.director.email == this.emailLogged){
           return true;
        }        
        return false;
    }

    
}